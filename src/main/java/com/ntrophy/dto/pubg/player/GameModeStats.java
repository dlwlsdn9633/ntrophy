package com.ntrophy.dto.pubg.player;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GameModeStats {
    private Solo solo;
    private Duo duo;

    private Squad squad;
}
