package com.ntrophy.service;

import com.ntrophy.client.pubg.facade.PubgApiClient;
import com.ntrophy.domain.match.Match;
import com.ntrophy.domain.match.MatchRoster;
import com.ntrophy.dto.pubg.enums.Platform;
import com.ntrophy.dto.pubg.match.MatchDto;
import com.ntrophy.dto.pubg.match.MatchIncluded;
import com.ntrophy.dto.pubg.match.MatchResponseDto;
import com.ntrophy.dto.pubg.player.PlayerDto;
import com.ntrophy.dto.pubg.player.PlayerMatchData;
import com.ntrophy.util.Function;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class MatchService {
    private final PubgApiClient pubgApiClient;
    private final ConversionService conversionService;
    private final ExecutorService executorService = Executors.newFixedThreadPool(10);
    public List<Match> getMatches(Platform platform, String accountId) {
        List<Match> matchList = new ArrayList<>();
        PlayerDto playerDto = pubgApiClient.getPlayer(platform, accountId);

        List<PlayerMatchData> matchesData = playerDto.getRelationships().getMatches().getData();
        List<Future<Match>> futures = new ArrayList<>();

        for (PlayerMatchData matchData : matchesData) {
            Future<Match> future = executorService.submit(() -> {
                MatchResponseDto dto = pubgApiClient.getMatch(platform, matchData.getId());
                Match match = conversionService.convert(dto, Match.class);
                match.getRosters().sort(Comparator.comparing(MatchRoster::getRank));
                findRosterByPlayerId(match, accountId).ifPresent(match::setMyRoster);
                return match;
            });
            futures.add(future);
        }

        for (Future<Match> future : futures) {
            try {
                matchList.add(future.get());
            } catch (InterruptedException | ExecutionException e) {
                log.error("Error While Fetching Match: {}", e.getMessage());
            }
        }
        return matchList;
    }

    public Optional<MatchRoster> findRosterByPlayerId(Match match, String accountId) {
        return match.getRosters().stream()
                .filter(roster -> roster.getParticipants().stream()
                        .anyMatch(p -> p.getPlayerId().equals(accountId)))
                .findFirst();
    }
    @PreDestroy
    public void shutdownExecutor() {
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(10, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}