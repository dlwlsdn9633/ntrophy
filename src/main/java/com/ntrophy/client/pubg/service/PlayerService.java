package com.ntrophy.client.pubg.service;

import com.ntrophy.client.pubg.core.PubgApiRequester;
import com.ntrophy.dto.pubg.enums.Platform;
import com.ntrophy.dto.pubg.player.PlayerDto;
import com.ntrophy.dto.pubg.player.PlayerResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PlayerService {
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
    public String getAccountIdByName(Platform platform, String name) {
        try {
            String uri = String.format("/shards/%s/players?filter[playerNames]=%s", platform.getLabel(), name);
            PlayerResponseDto response = requester.get(uri, PlayerResponseDto.class);
            return response.getData().getId();
        } catch (Exception e) {
            log.error("Failed To Fetch Account ID", e);
            return null;
        }
    }
}
