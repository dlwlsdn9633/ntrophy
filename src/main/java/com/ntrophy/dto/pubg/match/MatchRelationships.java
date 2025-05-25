package com.ntrophy.dto.pubg.match;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MatchRelationships {
    private MatchRosters rosters;
    private MatchTeam team;
    private MatchParticipants participants;
}
