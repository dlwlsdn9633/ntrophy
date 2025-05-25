package com.ntrophy.client.pubg.service;

import com.ntrophy.client.pubg.core.PubgApiRequester;
import com.ntrophy.dto.pubg.enums.Platform;
import com.ntrophy.dto.pubg.match.MatchDto;
import com.ntrophy.dto.pubg.match.MatchResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PubgMatchService {
    private final PubgApiRequester requester;
    public MatchResponseDto getMatch(Platform platform, String matchId) {
        try {
            String uri = String.format("/shards/%s/matches/%s", platform.getLabel(), matchId);
            return requester.get(uri, MatchResponseDto.class);
        } catch (Exception e) {
            log.error("Failed To Fetch Match Data {}", e.getMessage());
            return null;
        }
    }
}
