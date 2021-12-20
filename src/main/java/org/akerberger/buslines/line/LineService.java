package org.akerberger.buslines.line;

import com.google.gson.Gson;
import org.akerberger.buslines.journeypoint.JourneyPointService;
import org.akerberger.buslines.stop.StopName;
import org.akerberger.buslines.journeypoint.JourneyPointResponse;
import org.akerberger.buslines.stop.StopPointResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

@Service
public class LineService {

    private WebClient.Builder webClientBuilder;
    private LineParser busLineParser;

    private JourneyPointService journeyPointService;

    public LineService(WebClient.Builder webClientBuilder, LineParser busLineParser, JourneyPointService journeyPointService) {
        this.webClientBuilder = webClientBuilder;
        this.busLineParser = busLineParser;
        this.journeyPointService = journeyPointService;
    }

    public List<Line> linesWithMostStops(int topListSize) {

        JourneyPointResponse journeyPointResponse = journeyPointService.fetchAllJourneyPointsOnLines();

        List<Line> allLinesWithStopPoints = busLineParser.addStopPointsToLines(journeyPointResponse.getResults());

        return busLineParser.linesWithMostStops(allLinesWithStopPoints, topListSize);
    }

    /**
     * Since the StopPoints in a Line doesn't contain the name of the stop, we need to make
     * that connection here.
     *
     * @return The names of every stop in the line.
     */
    public List<StopName> busStopNames(Line line) {

        List<StopPointResponse.Result> allStopPoints = fetchAllStopPoints();

        return parseBusStopNamesForSpecifiedLine(line, allStopPoints);
    }

    private List<StopPointResponse.Result> fetchAllStopPoints() {
        String responseJson = webClientBuilder
                .build()
                .get()
                .uri(uriBuilder -> uriBuilder.path("/api2/LineData.json")
                        .queryParam("model", "stop")
                        .queryParam("key", "5da196d47f8f4e5facdb68d2e25b9eae")
                        .queryParam("DefaultTransportModeCode", "BUS")
                        .build()
                )
                .header("Accept-Encoding", "gzip, deflate")
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return new Gson().fromJson(responseJson, StopPointResponse.class).getResults();
    }



    private List<StopName> parseBusStopNamesForSpecifiedLine(Line line, List<StopPointResponse.Result> stopPointResponseList) {

        List<StopName> stopNames = new ArrayList<>();

        stopPointResponseList.stream()
                .forEach(result -> {
                    int stopPointNr = Integer.parseInt(result.getStopPointNumber());

                    if (line.containsStop(stopPointNr)) {
                        //Check for duplicates since the same stop name in another direction could be present on the line.
                        if (!busStopNameAlreadyPresentOnLine(stopNames, result.getStopPointName())) {
                            stopNames.add(new StopName(stopPointNr, result.getStopPointName()));
                        }
                    }
                });

        return stopNames;
    }

    private boolean busStopNameAlreadyPresentOnLine(List<StopName> stopNames, String stopPointName) {
        return stopNames.stream()
                .filter(stopName -> stopName.getStopPointName().equals(stopPointName))
                .findFirst()
                .isPresent();
    }


}
