package com.ntrophy.dto.pubg.player;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ntrophy.dto.pubg.ErrorDto;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlayerResponseListDto {
    private List<PlayerDto> data;
    private List<ErrorDto> errors;
}
