package com.ntrophy.dto.pubg.match;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MatchIncluded {
    private String type;
    private String id;
    private MatchAttributes attributes;
    private MatchRelationships relationships;
}
