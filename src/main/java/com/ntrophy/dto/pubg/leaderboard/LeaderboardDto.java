package com.ntrophy.dto.pubg.leaderboard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LeaderboardDto {
    private String type;
    private String id;

}
