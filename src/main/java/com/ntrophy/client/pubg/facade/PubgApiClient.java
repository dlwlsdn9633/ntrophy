package com.ntrophy.client.pubg.facade;

import com.ntrophy.client.pubg.service.LeaderboardService;
import com.ntrophy.client.pubg.service.PubgMatchService;
import com.ntrophy.client.pubg.service.PubgPlayerService;
import com.ntrophy.client.pubg.service.PubgSeasonService;
import com.ntrophy.dto.pubg.enums.GameMode;
import com.ntrophy.dto.pubg.enums.Platform;
import com.ntrophy.dto.pubg.enums.PlatformRegion;
import com.ntrophy.dto.pubg.match.MatchResponseDto;
import com.ntrophy.dto.pubg.player.PlayerDto;
import com.ntrophy.dto.pubg.season.SeasonDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PubgApiClient {
    private final PubgPlayerService pubgPlayerService;
    private final PubgSeasonService pubgSeasonService;
    private final LeaderboardService leaderboardService;
    private final PubgMatchService pubgMatchService;

    public List<PlayerDto> getTopNPlayers(
            PlatformRegion platformRegion,
            GameMode gameMode,
            String seasonId,
            int topN
    ) {
        return leaderboardService.getTopNPlayers(platformRegion, gameMode, seasonId, topN);
    }
    public PlayerDto getPlayer(Platform platform, String accountId) {
        return pubgPlayerService.getPlayer(platform, accountId);
    }
    public String getAccountIdByName(Platform platform, String name) {
        return pubgPlayerService.getAccountIdByName(platform, name);
    }
    public SeasonDto getCurrentSeason(Platform platform) {
        return pubgSeasonService.getCurrentSeason(platform);
    }
    public List<SeasonDto> getSeasonList(Platform platform) {
        return pubgSeasonService.getSeasonList(platform);
    }
    public PlayerDto getPlayerBySeasonIdRank(Platform platform, String accountId, String seasonId) {
        return pubgPlayerService.getPlayerBySeasonIdRank(platform, accountId, seasonId);
    }
    public PlayerDto getPlayerBySeasonId(Platform platform, String accountId, String seasonId) {
        return pubgPlayerService.getPlayerBySeasonId(platform, accountId, seasonId);
    }
    public MatchResponseDto getMatch(Platform platform, String matchId) {
        return pubgMatchService.getMatch(platform, matchId);
    }

}
