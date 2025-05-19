package com.ntrophy.client.pubg.service;

import com.ntrophy.client.pubg.core.PubgApiRequester;
import com.ntrophy.dto.pubg.enums.GameMode;
import com.ntrophy.dto.pubg.enums.PlatformRegion;
import com.ntrophy.dto.pubg.leaderboard.LeaderboardResponseDto;
import com.ntrophy.dto.pubg.player.PlayerDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class LeaderboardService {
    private final PubgApiRequester requester;
    public List<PlayerDto> getTopNPlayers(
            PlatformRegion platformRegion,
            GameMode gameMode,
            String seasonId,
            int topN
    )
    {
        try {
            String uri = String.format("/shards/%s/leaderboards/%s/%s",
                    platformRegion.getLabel(),
                    seasonId,
                    gameMode.getLabel()
            );
            LeaderboardResponseDto response = requester.get(uri, LeaderboardResponseDto.class);
            List<PlayerDto> players = response.getIncluded();
            players.sort(Comparator.comparing(p -> p.getAttributes().getRank()));
            return players.subList(0, Math.min(topN, players.size()));
        } catch (Exception e) {
            log.error("Failed To Fetch Leaderboard Data ", e);
            return List.of();
        }
    }
}
