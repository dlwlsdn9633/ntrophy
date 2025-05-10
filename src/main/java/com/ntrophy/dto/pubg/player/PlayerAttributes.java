package com.ntrophy.dto.pubg.player;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlayerAttributes {
    private String name;
    private int rank;
    private PlayerStats stats;
}
