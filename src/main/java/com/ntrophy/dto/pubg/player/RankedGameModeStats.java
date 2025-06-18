package com.ntrophy.dto.pubg.player;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RankedGameModeStats {
    private RankGameModeStats all;

    public RankGameModeStats getAll() {
        return all;
    }

    public void setAll(RankGameModeStats all) {
        this.all = all;
    }
}
