package com.ntrophy.dto.pubg.player;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Duo {
    private int assists;
    private int boosts;
    private int dBNOs;
    private int dailyKills;
    private int dailyWins;
    private int damageDealt;
    private int days;
    private int headshotKills;
    private int heals;
    private int killPoints;
    private int kills;
    private int longestKill;
    private int longestTimeSurvived;
    private int losses;
    private int maxKillStreaks;
    private int mostSurvivalTime;
    private int rankPoints;
    private String rankPointsTitle;
    private int revives;
    private int rideDistance;
    private int roadKills;
    private int roundMostKills;
    private int roundsPlayed;
    private int suicides;
    private int swimDistance;
    private int teamKills;
    private int timeSurvived;
    private int top10s;
    private int vehicleDestroys;
    private int walkDistance;
    private int weaponsAcquired;
    private int weeklyKills;
    private int weeklyWins;
    private int winPoints;
    private int wins;
}
