package com.ntrophy.converter;

import com.ntrophy.domain.member.PlayerDuo;
import com.ntrophy.domain.member.PlayerSolo;
import com.ntrophy.dto.pubg.player.Duo;
import com.ntrophy.dto.pubg.player.PlayerDto;
import com.ntrophy.dto.pubg.player.Solo;
import com.ntrophy.util.Function;
import org.springframework.core.convert.converter.Converter;

public class SeasonInfoToDuo implements Converter<PlayerDto, PlayerDuo> {
    @Override
    public PlayerDuo convert(PlayerDto source) {
        try {
            Duo duo = source.getAttributes().getGameModeStats().getDuo();
            double avgDamageDealt = Function.getRoundedDouble((double) duo.getDamageDealt() / (double) duo.getRoundsPlayed());
            double headshotKillsRatio = Function.getRoundedDouble((double) duo.getHeadshotKills() / (double) duo.getKills());
            double top10Ratio = Function.getRoundedDouble((double) duo.getTop10s() / duo.getRoundsPlayed());
            double kd = Function.getRoundedDouble((double) duo.getKills() / (double) duo.getLosses());
            double winsRatio = Function.getRoundedDouble((double) duo.getWins() / (double) duo.getRoundsPlayed());

            return PlayerDuo.builder()
                    .damageDealt(duo.getDamageDealt())
                    .longestKill(duo.getLongestKill())
                    .roundsPlayed(duo.getRoundsPlayed())
                    .headshotKills(duo.getHeadshotKills())
                    .kills(duo.getKills())
                    .losses(duo.getLosses())
                    .top10s(duo.getTop10s())
                    .headshotKillsRatio(headshotKillsRatio)
                    .avgDamageDealt(avgDamageDealt)
                    .top10Ratio(top10Ratio)
                    .kd(kd)
                    .winsRatio(winsRatio)
                    .longestTimeSurvived(duo.getLongestTimeSurvived())
                    .build();
        } catch (Exception e) {
            return PlayerDuo.builder().build();
        }
    }
}
