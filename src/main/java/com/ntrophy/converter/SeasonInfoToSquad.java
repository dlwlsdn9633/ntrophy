package com.ntrophy.converter;

import com.ntrophy.domain.member.PlayerSquad;
import com.ntrophy.dto.pubg.player.PlayerDto;
import com.ntrophy.dto.pubg.player.Squad;
import com.ntrophy.util.Function;
import org.springframework.core.convert.converter.Converter;

public class SeasonInfoToSquad implements Converter<PlayerDto, PlayerSquad> {
    @Override
    public PlayerSquad convert(PlayerDto source) {
        try {
            Squad squad = source.getAttributes().getGameModeStats().getSquad();
            double avgDamageDealt = Function.getRoundedDouble((double)squad.getDamageDealt() / (double)squad.getRoundsPlayed());
            double headshotKillsRatio = Function.getRoundedDouble((double)squad.getHeadshotKills() / (double)squad.getKills());
            double top10Ratio = Function.getRoundedDouble((double) squad.getTop10s() / (double) squad.getRoundsPlayed());
            double kd = Function.getRoundedDouble((double) squad.getKills() / (double) squad.getLosses());
            double winsRatio = Function.getRoundedDouble((double) squad.getWins() / (double) squad.getRoundsPlayed());
                    

            return PlayerSquad.builder()
                    .damageDealt(squad.getDamageDealt())
                    .longestKill(squad.getLongestKill())
                    .roundsPlayed(squad.getRoundsPlayed())
                    .headshotKills(squad.getHeadshotKills())
                    .kills(squad.getKills())
                    .losses(squad.getLosses())
                    .top10s(squad.getTop10s())
                    .headshotKillsRatio(headshotKillsRatio)
                    .avgDamageDealt(avgDamageDealt)
                    .top10Ratio(top10Ratio)
                    .kd(kd)
                    .winsRatio(winsRatio)
                    .longestTimeSurvived(squad.getLongestTimeSurvived())
                    .build();
        } catch (Exception e) {
            return PlayerSquad.builder().build();
        }
    }
}
