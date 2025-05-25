package com.ntrophy.converter;

import com.ntrophy.domain.member.PlayerSolo;
import com.ntrophy.dto.pubg.player.PlayerDto;

import com.ntrophy.dto.pubg.player.Solo;
import com.ntrophy.util.Function;
import org.springframework.core.convert.converter.Converter;

public class SeasonInfoToSolo implements Converter<PlayerDto, PlayerSolo> {
    @Override
    public PlayerSolo convert(PlayerDto source) {
        try {
            Solo solo = source.getAttributes().getGameModeStats().getSolo();
            double avgDamageDealt = Function.getRoundedDouble((double) solo.getDamageDealt() / (double) solo.getRoundsPlayed());
            double headshotKillsRatio = Function.getRoundedDouble((double) solo.getHeadshotKills() / (double) solo.getKills());
            double top10Ratio = Function.getRoundedDouble((double) solo.getTop10s() / solo.getRoundsPlayed());
            double kd = Function.getRoundedDouble((double) solo.getKills() / (double) solo.getLosses());
            double winsRatio = Function.getRoundedDouble((double) solo.getWins() / (double) solo.getRoundsPlayed());

            return PlayerSolo.builder()
                    .damageDealt(solo.getDamageDealt())
                    .longestKill(solo.getLongestKill())
                    .roundsPlayed(solo.getRoundsPlayed())
                    .headshotKills(solo.getHeadshotKills())
                    .kills(solo.getKills())
                    .losses(solo.getLosses())
                    .top10s(solo.getTop10s())
                    .headshotKillsRatio(headshotKillsRatio)
                    .avgDamageDealt(avgDamageDealt)
                    .top10Ratio(top10Ratio)
                    .kd(kd)
                    .winsRatio(winsRatio)
                    .longestTimeSurvived(solo.getLongestTimeSurvived())
                    .build();
        } catch (Exception e) {
            return PlayerSolo.builder().build();
        }
    }
}
