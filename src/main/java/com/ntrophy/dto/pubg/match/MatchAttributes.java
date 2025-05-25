package com.ntrophy.dto.pubg.match;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MatchAttributes {
    private MatchStats stats; // null 가능
    private String gameMode;
    private String shardId;
    private Object tags;  // null 가능
    private boolean isCustomMatch;
    private String createdAt;
    private int duration;
    private String matchType;
    private String seasonState;
    private String titleId;
    private String mapName;
    private String actor;
    private boolean won;
    
}
