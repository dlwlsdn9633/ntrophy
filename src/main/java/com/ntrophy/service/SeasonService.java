package com.ntrophy.service;

import com.ntrophy.client.pubg.facade.PubgApiClient;
import com.ntrophy.domain.season.Season;
import com.ntrophy.dto.pubg.enums.Platform;
import com.ntrophy.dto.pubg.season.SeasonDto;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SeasonService {
    private final PubgApiClient pubgApiClient;
    private final ConversionService conversionService;
    public List<Season> getSeasonList(Platform platform) {
        List<Season> seasonList = new ArrayList<>();
        for (SeasonDto seasonDto : pubgApiClient.getSeasonList(platform)) {
            seasonList.add(conversionService.convert(seasonDto, Season.class));
        }
        return seasonList;
    }
}
