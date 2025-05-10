package com.ntrophy.dto.pubg.season;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SeasonAttributes {
    @JsonProperty("isCurrentSeason")  // JSON 필드명과 일치시킴
    private boolean isCurrentSeason;
    @JsonProperty("isOffseason")
    private boolean isOffseason;
}