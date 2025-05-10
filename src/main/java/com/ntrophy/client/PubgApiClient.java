package com.ntrophy.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ntrophy.dto.pubg.enums.GameMode;
import com.ntrophy.dto.pubg.enums.PlatformRegion;
import com.ntrophy.dto.pubg.enums.Platform;
import com.ntrophy.dto.pubg.leaderboard.LeaderboardResponseDto;
import com.ntrophy.dto.pubg.player.PlayerDto;
import com.ntrophy.dto.pubg.season.SeasonDto;
import com.ntrophy.dto.pubg.season.SeasonResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Comparator;
import java.util.List;

@Slf4j
@Component
public class PubgApiClient {
    private static final String PUBG_BASE_URL = "https://api.pubg.com";
    private final WebClient webClient;
    private final ObjectMapper objectMapper = new ObjectMapper();
    public PubgApiClient(@Value("${api.pubg-key}") String apiKey) {
        log.info("Pubg Api Client Complete Created");
        this.webClient = WebClient.builder()
                .baseUrl(PUBG_BASE_URL)
                .defaultHeader("Authorization", "Bearer " + apiKey)
                .defaultHeader("Accept", "application/vnd.api+json")
                .build();
    }
    public List<PlayerDto> getTop10Players(PlatformRegion platformRegion, GameMode gameMode, String seasonId) {
        try {
            String URI = String.format("/shards/%s/leaderboards/%s/%s", platformRegion.getLabel(), seasonId, gameMode.getLabel());
            String response = webClient.get()
                    .uri(URI)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            LeaderboardResponseDto responseDto = objectMapper.readValue(response, LeaderboardResponseDto.class);
            List<PlayerDto> playerDtoList = responseDto.getIncluded();
            playerDtoList.sort(Comparator.comparingInt(p -> p.getAttributes().getRank()));
            return playerDtoList.subList(0, Math.min(10, playerDtoList.size()));
        } catch (Exception e) {
            log.error("Failed To Fetch PUBG leaderboard data ", e);
            return List.of();
        }
    }
    public List<PlayerDto> getTopNPlayers(PlatformRegion platformRegion, GameMode gameMode, String seasonId, int topNumbers) {
        try {
            String URI = String.format("/shards/%s/leaderboards/%s/%s", platformRegion.getLabel(), seasonId, gameMode.getLabel());
            String response = webClient.get()
                    .uri(URI)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            LeaderboardResponseDto responseDto = objectMapper.readValue(response, LeaderboardResponseDto.class);
            List<PlayerDto> playerDtoList = responseDto.getIncluded();
            playerDtoList.sort(Comparator.comparingInt(p -> p.getAttributes().getRank()));
            return playerDtoList.subList(0, Math.min(topNumbers, playerDtoList.size()));
        } catch (Exception e) {
            log.error("Failed To Fetch PUBG leaderboard data ", e);
            return List.of();
        }
    }
    public SeasonDto getCurrentSeason(Platform platform) {
        try {
            String URI = String.format("/shards/%s/seasons", platform.getLabel());
            String response = webClient.get()
                    .uri(URI)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            SeasonResponseDto responseDto = objectMapper.readValue(response, SeasonResponseDto.class);
            return extractCurrentSeason(responseDto);
        } catch (Exception e) {
            log.error("Failed To Fetch PUBG season data ", e);
            return null;
        }
    }
    private SeasonDto extractCurrentSeason(SeasonResponseDto responseDto) {
        return responseDto.getData().stream()
                .filter(season -> season.getAttributes().isCurrentSeason())
                .findFirst()
                .orElse(null);
    }
}
