package com.ntrophy.domain.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class PlayerDuo {
    private double damageDealt;
    private double avgDamageDealt;
    private double longestKill;
    private double headshotKillsRatio;
    private double top10Ratio;
    private double winsRatio;
    private int roundsPlayed;
    private int headshotKills;
    private int kills;
    private int losses;
    private int top10s;
    private double kd;
    private int longestTimeSurvived;
}
