package com.ntrophy.dto.pubg.season;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SeasonDto {
    private String type;
    private String id;
    private SeasonAttributes attributes;
}
