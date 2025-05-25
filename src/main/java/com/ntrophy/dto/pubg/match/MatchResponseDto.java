package com.ntrophy.dto.pubg.match;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MatchResponseDto {
    private MatchDto data;
    private List<MatchIncluded> included;
}
