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
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MatchService {
    private final PubgApiClient pubgApiClient;
    private final ConversionService conversionService;
    public List<Match> getMatches(Platform platform, String accountId) {
        List<Match> matchList = new ArrayList<>();
        PlayerDto playerDto = pubgApiClient.getPlayer(platform, accountId);

        for (PlayerMatchData matchData : playerDto.getRelationships().getMatches().getData()) {
            MatchResponseDto matchResponseDto = pubgApiClient.getMatch(platform, matchData.getId());
            Match match = conversionService.convert(matchResponseDto, Match.class);
            match.getRosters().sort((roster1, roster2) -> {
                return roster1.getRank() - roster2.getRank();
            });
            findRosterByPlayerId(match, accountId).ifPresent(match::setMyRoster);
            matchList.add(match);
        }

        return matchList;
    }

    public Optional<MatchRoster> findRosterByPlayerId(Match match, String accountId) {
        return match.getRosters().stream()
                .filter(roster -> roster.getParticipants().stream()
                        .anyMatch(p -> p.getPlayerId().equals(accountId)))
                .findFirst();
    }
}
