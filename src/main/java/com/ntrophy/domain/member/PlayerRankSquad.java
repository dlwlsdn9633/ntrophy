package com.ntrophy.domain.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlayerRankSquad {
    private int currentRankPoint;
    private String tier;
    private String subTier;
    private double kda;
    private double wins;
    private double winRatio;
    private double top10Ratio;
    private double damageDealt;
    private double avgRank;
    private int roundsPlayed;
    private int roundMostKills;
    private double headshotKillRatio;
    private int longestKill;
    private int kills;
    private double avgDamageDealt;
}
