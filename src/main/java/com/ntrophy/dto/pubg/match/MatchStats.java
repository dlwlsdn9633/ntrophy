package com.ntrophy.dto.pubg.match;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MatchStats {
    private int DBNOs;
    private int assists;
    private int boosts;
    private double damageDealt;
    private String deathType;
    private int headshotKills;
    private int heals;
    private int killPlace;
    private int killStreaks;
    private int kills;
    private double longestKill;
    private String name;
    private String playerId;
    private int revives;
    private double rideDistance;
    private int roadKills;
    private double swimDistance;
    private int teamKills;
    private int timeSurvived;
    private int vehicleDestroys;
    private double walkDistance;
    private int weaponsAcquired;
    private int winPlace;
    private int rank;
    private int teamId;

}
