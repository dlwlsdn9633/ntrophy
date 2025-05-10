package com.ntrophy.dto.pubg.leaderboard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ntrophy.dto.pubg.player.PlayerDto;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LeaderboardResponseDto {
    private LeaderboardDto data;
    private List<PlayerDto> included;
    private Map<String, Object> files;
    private Map<String, Object> meta;
}
