package com.ntrophy.service;

import com.ntrophy.client.pubg.facade.PubgApiClient;
import com.ntrophy.domain.match.Match;
import com.ntrophy.dto.pubg.enums.Platform;
import com.ntrophy.dto.pubg.match.MatchDto;
import com.ntrophy.dto.pubg.match.MatchResponseDto;
import com.ntrophy.dto.pubg.player.PlayerDto;
import com.ntrophy.dto.pubg.player.PlayerMatchData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
            matchList.add(match);
        }
        return matchList;
    }
}
