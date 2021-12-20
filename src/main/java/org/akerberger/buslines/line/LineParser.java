package org.akerberger.buslines.line;

import org.akerberger.buslines.stop.StopPoint;
import org.akerberger.buslines.journeypoint.JourneyPointResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Component
public class LineParser {
    private static final Logger log = LoggerFactory.getLogger(LineParser.class);

    /**
     * Parses the JourneyPoints into StopPoints and adds them to the correct Line
     */
    public List<Line> addStopPointsToLines(List<JourneyPointResponse.JourneyPointResult> pointsOnLineResponse) {

        List<Line> lines = new LinkedList<>();

        pointsOnLineResponse.stream()
                .forEach(journeyPointResult -> {
                    int lineNumber = Integer.parseInt(journeyPointResult.getLineNumber());
                    addStopPointToLine(lines, toStopPoint(journeyPointResult), lineNumber);
                });
        return lines;
    }

    /**
     * The resulting list is sorted in descending order, i.e. line with most stop is at index 0
     */
    public List<Line> linesWithMostStops(List<Line> lines, int topListSize) {

        //descending order
        lines.sort((o1, o2) -> Integer.compare(o2.getNumberOfStops(), o1.getNumberOfStops()));

        List<Line> lineWithMostStops = new ArrayList<>();
        for (int i = 0; i < topListSize; i++) {
            lineWithMostStops.add(lines.get(i));
        }
        return lineWithMostStops;

    }

    private void addStopPointToLine(List<Line> lines, StopPoint stopPoint, int lineNr) {
        Optional<Line> busLineOptional = lines.stream()
                .filter(line -> line.getLineNumber() == lineNr)
                .findFirst();

        if (busLineOptional.isPresent()) {
            busLineOptional.get().addBusStop(stopPoint);
        } else {
            Line line = new Line(lineNr);
            line.addBusStop(stopPoint);
            lines.add(line);
        }
    }

    private StopPoint toStopPoint(JourneyPointResponse.JourneyPointResult journeyPointResult) {

        int journeyPatternPointNr = Integer.parseInt(journeyPointResult.getJourneyPatternPointNumber());
        int directionCode = Integer.parseInt(journeyPointResult.getDirectionCode());

        return new StopPoint(journeyPatternPointNr, directionCode);
    }
}
