package com.ntrophy.client.pubg.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

// API 요청 추상화 공통 모듈 생성
@Component
public class PubgApiRequester {
    private final WebClient webClient;
    private final ObjectMapper objectMapper = new ObjectMapper();
    public PubgApiRequester(@Value("${api.pubg-key}") String apiKey) {
        this.webClient = WebClient.builder()
                .baseUrl("https://api.pubg.com")
                .defaultHeader("Authorization", "Bearer " + apiKey)
                .defaultHeader("Accept", "application/vnd.api+json")
                .build();
    }
    public <T> T get(String uri, Class<T> responseType) throws Exception {
        String response = webClient.get().uri(uri).retrieve().bodyToMono(String.class).block();
        return objectMapper.readValue(response, responseType);
    }
}
