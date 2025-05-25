package com.ntrophy.domain.match;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class MatchRoster {
    private String id;
    private int rank;
    private int teamId;
    private boolean won;
    private String shardId;
    private List<MatchParticipant> participants;
}
