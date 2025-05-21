package com.ntrophy.service;

import com.ntrophy.client.pubg.facade.PubgApiClient;
import com.ntrophy.dto.pubg.player.PlayerDto;
import com.ntrophy.dto.pubg.enums.GameMode;
import com.ntrophy.dto.pubg.enums.PlatformRegion;
import com.ntrophy.dto.pubg.enums.Platform;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class PubgService {
    private final PubgApiClient pubgApiClient;
    public PlayerDto getPlayer(String accountId) {
        return pubgApiClient.getPlayer(Platform.STEAM, accountId);
    }
    public List<PlayerDto> getTopNPlayers(PlatformRegion platformRegion, Platform platform, GameMode gameMode, int topNumbers) {
        String seasonId = getCurrentSeasonId(platform);
        if (seasonId == null) {
            return Collections.emptyList();
        }
        return pubgApiClient.getTopNPlayers(platformRegion, gameMode, seasonId, topNumbers);
    }
    public PlayerDto getPlayerBySeasonId(String accountId) {
        PlayerDto player = getPlayer(accountId);
        Platform playerPlatform = Platform.fromLabel(player.getAttributes().getShardId());
        String seasonId = getCurrentSeasonId(playerPlatform);

        if (seasonId == null) {
            return null;
        }
        return pubgApiClient.getPlayerBySeasonId(playerPlatform, accountId, seasonId);
    }
    public PlayerDto getPlayerByName(String name) {
        String steamAccountId = pubgApiClient.getAccountIdByName(Platform.STEAM, name);
        //String kakaoAccountId = pubgApiClient.getAccountIdByName(Platform.KAKAO, name);
        if (steamAccountId != null) {
            return pubgApiClient.getPlayer(Platform.STEAM, steamAccountId);
        }
        /*
        if (kakaoAccountId != null) {
            return pubgApiClient.getPlayer(Platform.KAKAO, kakaoAccountId);
        }
        */

        return null;
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
