package com.ntrophy.converter;

import com.ntrophy.domain.member.PlayerRankSquad;
import com.ntrophy.dto.pubg.player.RankGameModeStats;
import com.ntrophy.dto.pubg.player.PlayerDto;
import com.ntrophy.util.Function;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SeasonInfoToRankSquad implements Converter<PlayerDto, PlayerRankSquad> {
    @Override
    public PlayerRankSquad convert(PlayerDto source) {
        try {
            RankGameModeStats rankedStats = source.getAttributes().getRankedGameModeStats().getAll();

            return PlayerRankSquad.builder()
                    .avgRank(Function.getRoundedDouble(rankedStats.getAvgRank()))
                    .currentRankPoint(rankedStats.getCurrentRankPoint())
                    .tier(rankedStats.getCurrentTier().getTier())
                    .subTier(rankedStats.getCurrentTier().getSubTier())
                    .kda(Function.getRoundedDouble(rankedStats.getKda()))
                    .wins(rankedStats.getWins())
                    .winRatio(Function.getRoundedDouble(rankedStats.getWinRatio() * 100.0))
                    .top10Ratio(Function.getRoundedDouble(rankedStats.getTop10Ratio() * 100.0))
                    .damageDealt(rankedStats.getDamageDealt())
                    .roundsPlayed(rankedStats.getRoundsPlayed())
                    .roundMostKills(rankedStats.getRoundMostKills())
                    .headshotKillRatio(rankedStats.getHeadshotKillRatio())
                    .longestKill(rankedStats.getLongestKill())
                    .avgDamageDealt(Function.getRoundedDouble(
                            rankedStats.getDamageDealt() / (double) rankedStats.getRoundsPlayed()))
                    .kills(rankedStats.getKills())
                    .build();
        } catch (Exception e) {
            return PlayerRankSquad.builder().build();
        }
    }
}
