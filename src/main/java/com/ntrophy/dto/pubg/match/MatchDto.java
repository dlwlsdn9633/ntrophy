package com.ntrophy.dto.pubg.match;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MatchDto {
    String type;
    String id;
    MatchAttributes attributes;
    MatchRelationships relationships;
}
