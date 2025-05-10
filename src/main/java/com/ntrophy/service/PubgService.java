package com.ntrophy.service;

import com.ntrophy.client.PubgApiClient;
import com.ntrophy.dto.pubg.player.PlayerDto;
import com.ntrophy.dto.pubg.enums.GameMode;
import com.ntrophy.dto.pubg.enums.PlatformRegion;
import com.ntrophy.dto.pubg.enums.Platform;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PubgService {
    private final PubgApiClient pubgApiClient;
    public List<PlayerDto> getTop10Players(PlatformRegion platformRegion, Platform platform, GameMode gameMode) {
        String seasonId = getCurrentSeasonId(platform);
        if (seasonId == null) {
            return Collections.emptyList();
        }
        return pubgApiClient.getTop10Players(platformRegion, gameMode, seasonId);
    }
    public List<PlayerDto> getTopNPlayers(PlatformRegion platformRegion, Platform platform, GameMode gameMode, int topNumbers) {
        String seasonId = getCurrentSeasonId(platform);
        if (seasonId == null) {
            return Collections.emptyList();
        }
        return pubgApiClient.getTopNPlayers(platformRegion, gameMode, seasonId, topNumbers);
    }
    private String getCurrentSeasonId(Platform platform) {
        try {
            return pubgApiClient.getCurrentSeason(platform).getId();
        } catch (Exception e) {
            log.error("Failed To Fetch Current Season For Platform: {}", platform);
            return null;
        }
    }
}
