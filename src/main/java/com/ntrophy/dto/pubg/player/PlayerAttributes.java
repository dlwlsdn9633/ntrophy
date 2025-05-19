package com.ntrophy.dto.pubg.player;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlayerAttributes {
    private String name;
    private int rank;
    private String shardId;
    private PlayerStats stats;
    private String patchVersion;
    private String banType;
    private String titleId;
    private String clanId;
}
