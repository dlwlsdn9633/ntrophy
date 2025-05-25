package com.ntrophy.converter;

import com.ntrophy.domain.season.Season;
import com.ntrophy.dto.pubg.season.SeasonDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;


@Slf4j
public class SeasonDtoToSeason implements Converter<SeasonDto, Season> {
    @Override
    public Season convert(SeasonDto source) {
        try {
            return Season.builder()
                    .id(source.getId())
                    .name(Season.getSeasonNumber(source.getId()))
                    .isCurrentSeason(source.getAttributes().isCurrentSeason())
                    .isOffSeason(source.getAttributes().isOffseason())
                    .build();
        } catch (Exception e) {
            log.error("error: {}", e.getMessage());
            return Season.builder().build();
        }
    }
}
