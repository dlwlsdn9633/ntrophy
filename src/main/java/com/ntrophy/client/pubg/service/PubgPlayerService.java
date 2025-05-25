package com.ntrophy.client.pubg.service;

import com.ntrophy.client.pubg.core.PubgApiRequester;
import com.ntrophy.dto.pubg.enums.Platform;
import com.ntrophy.dto.pubg.player.PlayerDto;
import com.ntrophy.dto.pubg.player.PlayerResponseDto;
import com.ntrophy.dto.pubg.player.PlayerResponseListDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PubgPlayerService {
    private final PubgApiRequester requester;
    public PlayerDto getPlayer(Platform platform, String accountId) {
        try {
            String uri = String.format("/shards/%s/players/%s", platform.getLabel(), accountId);
            PlayerResponseDto response = requester.get(uri, PlayerResponseDto.class);
            return response.getData();
        } catch (Exception e) {
            log.error("Failed To Fetch Player Data", e);
            return null;
        }
    }
    public PlayerDto getPlayerBySeasonIdRank(Platform platform, String accountId, String seasonId) {
        try {
            String uri = String.format("/shards/%s/players/%s/seasons/%s/ranked", platform.getLabel(), accountId, seasonId);
            PlayerResponseDto response = requester.get(uri, PlayerResponseDto.class);
            return response.getData();
        } catch (Exception e) {
            log.error("Failed To Fetch Player Data", e);
            return null;
        }
    }
    public PlayerDto getPlayerBySeasonId(Platform platform, String accountId, String seasonId) {
        try {
            String uri = String.format("/shards/%s/players/%s/seasons/%s", platform.getLabel(), accountId, seasonId);
            PlayerResponseDto response = requester.get(uri, PlayerResponseDto.class);

            return response.getData();
        } catch (Exception e) {
            log.error("Failed To Fetch Player Data", e);
            return null;
        }
    }

    public String getAccountIdByName(Platform platform, String name) {
        try {
            String uri = String.format("/shards/%s/players?filter[playerNames]=%s", platform.getLabel(), name);
            PlayerResponseListDto response = requester.get(uri, PlayerResponseListDto.class);

            if (response.getErrors() != null) {
                return null;
            }
            return response.getData().get(0).getId();
        } catch (Exception e) {
            log.error("Failed To Fetch Account ID", e);
            return null;
        }
    }
}
