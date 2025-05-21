package com.ntrophy.client.pubg.facade;

import com.ntrophy.client.pubg.service.LeaderboardService;
import com.ntrophy.client.pubg.service.PlayerService;
import com.ntrophy.client.pubg.service.SeasonService;
import com.ntrophy.dto.pubg.enums.GameMode;
import com.ntrophy.dto.pubg.enums.Platform;
import com.ntrophy.dto.pubg.enums.PlatformRegion;
import com.ntrophy.dto.pubg.player.PlayerDto;
import com.ntrophy.dto.pubg.season.SeasonDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PubgApiClient {
    private final PlayerService playerService;
    private final SeasonService seasonService;
    private final LeaderboardService leaderboardService;

    public List<PlayerDto> getTopNPlayers(
            PlatformRegion platformRegion,
            GameMode gameMode,
            String seasonId,
            int topN
    ) {
        return leaderboardService.getTopNPlayers(platformRegion, gameMode, seasonId, topN);
    }
    public PlayerDto getPlayer(Platform platform, String accountId) {
        return playerService.getPlayer(platform, accountId);
    }
    public String getAccountIdByName(Platform platform, String name) {
        return playerService.getAccountIdByName(platform, name);
    }
    public SeasonDto getCurrentSeason(Platform platform) {
        return seasonService.getCurrentSeason(platform);
    }
    public PlayerDto getPlayerBySeasonId(Platform platform, String accountId, String seasonId) {
        return playerService.getPlayerBySeasonId(platform, accountId, seasonId);
    }
}
