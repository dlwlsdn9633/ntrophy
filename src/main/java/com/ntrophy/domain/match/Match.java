package com.ntrophy.domain.match;

import com.ntrophy.dto.pubg.match.MatchIncluded;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class Match {
    private String type;
    private String id;
    private MatchAttribute matchAttribute;
    private List<MatchRoster> rosters;
    private Map<String, MatchIncluded> participantsMap;
    private MatchRoster myRoster;
}
