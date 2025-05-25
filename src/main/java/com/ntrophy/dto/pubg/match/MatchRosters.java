package com.ntrophy.dto.pubg.match;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MatchRosters {
    private List<MatchRoster> data;
}
