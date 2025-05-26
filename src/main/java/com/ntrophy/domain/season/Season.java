package com.ntrophy.domain.season;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Season {
    private String id;
    private String name;
    private boolean isCurrentSeason;
    private boolean isOffSeason;
    public static String getSeasonNumber(String id) {
        try {
            String[] parts = id.split("-");
            return parts[parts.length - 1];
        } catch (Exception e) {
            return "";
        }
    }
    
}
