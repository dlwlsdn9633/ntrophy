package com.ntrophy.converter;

import com.ntrophy.domain.member.PlayerRankSquad;
import com.ntrophy.dto.pubg.player.PlayerAttributes;
import com.ntrophy.dto.pubg.player.PlayerDto;
import com.ntrophy.dto.pubg.player.Squad;
import com.ntrophy.util.Function;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


public class SeasonInfoToRankSquad implements Converter<PlayerDto, PlayerRankSquad> {
    @Override
    public PlayerRankSquad convert(PlayerDto source) {
        try {
            Squad squad = source.getAttributes().getRankedGameModeStats().getSquad();

            PlayerRankSquad playerRankSquad = PlayerRankSquad.builder()
                    .avgRank(Function.getRoundedDouble(squad.getAvgRank()))
                    .currentRankPoint(squad.getCurrentRankPoint())
                    .tier(squad.getCurrentTier().getTier())
                    .subTier(squad.getCurrentTier().getSubTier())
                    .kda(Function.getRoundedDouble(squad.getKda()))
                    .wins(squad.getWins())
                    .winRatio(Function.getRoundedDouble(squad.getWinRatio() * 100.0))
                    .top10Ratio(Function.getRoundedDouble(squad.getTop10Ratio() * 100.0))
                    .damageDealt(squad.getDamageDealt())
                    .roundsPlayed(squad.getRoundsPlayed())
                    .roundMostKills(squad.getRoundMostKills())
                    .headshotKillRatio(squad.getHeadshotKillRatio())
                    .longestKill(squad.getLongestKill())
                    .avgDamageDealt(Function.getRoundedDouble(squad.getDamageDealt() / (double) squad.getRoundsPlayed()))
                    .kills(squad.getKills())
                    .build();
            return playerRankSquad;
        } catch (Exception e) {
            return PlayerRankSquad.builder().build();
        }
    }
}
