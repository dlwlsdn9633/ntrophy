package com.ntrophy.dto.pubg.season;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SeasonResponseDto {
    private List<SeasonDto> data;
}
