package com.ntrophy.dto.pubg.player;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlayerStats {
    private double rankPoints;
    private int wins;
    private int games;
    private int averageDamage;
    private int kills;
    private double kda;
    private double averageRank;
    private String tier;
    private String subTier;

}
