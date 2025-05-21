package com.ntrophy.dto.pubg.player;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Squad {
    private CurrentTier currentTier;
    private int currentRankPoint;
    private BestTier bestTier;
    private int bestRankPoint;
    private int roundsPlayed;
    private double avgRank;
    private int avgSurvivalTime;
    private double top10Ratio;
    private double winRatio;
    private int assists;
    private int wins;
    private double kda;
    private int kdr;
    private int kills;
    private int deaths;
    private int roundMostKills;
    private int longestKill;
    private int headshotKills;
    private double headshotKillRatio;
    private double damageDealt;
    private int dBNOs;
    private double reviveRatio;
    private int revives;
    private int heals;
    private int boosts;
    private int weaponsAcquired;
    private int teamKills;
    private int playTime;
    private int killStreak;
}
