package com.ntrophy.client.pubg.service;

import com.ntrophy.client.pubg.core.PubgApiRequester;
import com.ntrophy.dto.pubg.enums.Platform;
import com.ntrophy.dto.pubg.season.SeasonDto;
import com.ntrophy.dto.pubg.season.SeasonResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SeasonService {
    private final PubgApiRequester requester;
    public SeasonDto getCurrentSeason(Platform platform) {
        try {
            String uri = String.format("/shards/%s/seasons", platform.getLabel());
            SeasonResponseDto response = requester.get(uri, SeasonResponseDto.class);
            return response.getData().stream()
                    .filter(season  -> season.getAttributes().isCurrentSeason())
                    .findFirst()
                    .orElse(null);
        } catch (Exception e) {
            log.error("Failed To Fetch Season Data", e);
            return null;
        }
    }
}
