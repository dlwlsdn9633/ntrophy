package com.ntrophy.service;

import com.ntrophy.client.pubg.facade.PubgApiClient;
import com.ntrophy.dto.pubg.match.MatchDto;
import com.ntrophy.dto.pubg.player.PlayerDto;
import com.ntrophy.dto.pubg.enums.GameMode;
import com.ntrophy.dto.pubg.enums.PlatformRegion;
import com.ntrophy.dto.pubg.enums.Platform;
import com.ntrophy.dto.pubg.player.PlayerMatch;
import com.ntrophy.dto.pubg.player.PlayerMatchData;
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
    public PlayerDto getPlayer(Platform platform, String accountId) {
        // TODO: platform에 따라 player 정보를 가지고 오도록 refactoring
        PlayerDto playerDto = pubgApiClient.getPlayer(Platform.STEAM, accountId);
        return pubgApiClient.getPlayer(platform, accountId);
    }
    public List<PlayerDto> getTopNPlayers(PlatformRegion platformRegion, Platform platform, GameMode gameMode, int topNumbers) {
        String seasonId = getCurrentSeasonId(platform);
        if (seasonId == null) {
            return Collections.emptyList();
        }
        return pubgApiClient.getTopNPlayers(platformRegion, gameMode, seasonId, topNumbers);
    }
    public PlayerDto getPlayerRank(Platform platform, String accountId, String seasonId) {
        PlayerDto player = getPlayer(platform, accountId);
        Platform playerPlatform = Platform.fromLabel(player.getAttributes().getShardId());
        return pubgApiClient.getPlayerBySeasonIdRank(playerPlatform, accountId, seasonId);
    }
    public PlayerDto getPlayerNormal(Platform platform, String accountId, String seasonId) {
        PlayerDto player = getPlayer(platform, accountId);
        Platform playerPlatform = Platform.fromLabel(player.getAttributes().getShardId());
        return pubgApiClient.getPlayerBySeasonId(playerPlatform, accountId, seasonId);
    }
    public PlayerDto getPlayerByName(String name) {
        String steamAccountId = pubgApiClient.getAccountIdByName(Platform.STEAM, name);
        if (steamAccountId != null) {
            return pubgApiClient.getPlayer(Platform.STEAM, steamAccountId);
        }
        String kakaoAccountId = pubgApiClient.getAccountIdByName(Platform.KAKAO, name);
        if (kakaoAccountId != null) {
            return pubgApiClient.getPlayer(Platform.KAKAO, kakaoAccountId);
        }
        return null;
    }
    public String getCurrentSeasonId(Platform platform) {
        try {
            return pubgApiClient.getCurrentSeason(platform).getId();
        } catch (Exception e) {
            log.error("Failed To Fetch Current Season For Platform: {}", platform);
            return null;
        }
    }
}
