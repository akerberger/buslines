package org.akerberger.buslines;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class BusLinesApplication {

    public static void main(String[] args) {
        SpringApplication.run(BusLinesApplication.class, args);
    }

    @Bean
    public WebClient.Builder getWebClientBuilder() {
        final String BASE_URL = "https://api.sl.se";

        return WebClient.builder()
                .baseUrl(BASE_URL)
                .exchangeStrategies(ExchangeStrategies.builder()
                        .codecs(conf -> conf.defaultCodecs().maxInMemorySize(16 * 1024 * 1024))
                        .build());
    }

}
