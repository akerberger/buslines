package org.akerberger.buslines.journeypoint;

import com.google.gson.Gson;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class JourneyPointService {

    private WebClient.Builder webClientBuilder;

    public JourneyPointService(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    public JourneyPointResponse fetchAllJourneyPointsOnLines() {

        String responseJson = webClientBuilder
                .build()
                .get()
                .uri(uriBuilder -> uriBuilder.path("/api2/LineData.json")
                        .queryParam("model", "jour")
                        .queryParam("key", "5da196d47f8f4e5facdb68d2e25b9eae")
                        .queryParam("DefaultTransportModeCode", "Bus")
                        .build())
                .header("Accept-Encoding", "gzip, deflate")
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return new Gson().fromJson(responseJson, JourneyPointResponse.class);
    }
}
