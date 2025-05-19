package com.ntrophy.controller;

import com.ntrophy.dto.pubg.player.PlayerDto;
import com.ntrophy.dto.pubg.enums.GameMode;
import com.ntrophy.dto.pubg.enums.Platform;
import com.ntrophy.dto.pubg.enums.PlatformRegion;
import com.ntrophy.service.PubgService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.concurrent.*;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {
    private static final int LEADERBOARD_TOTAL_NUMBER = 50;
    private static final int HOME_LEADERBOARD_TOTAL_NUMBER = 10;
    private final PubgService pubgService;
    @GetMapping("/")
    public String home(Model model) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Callable<List<PlayerDto>> steamTask = () -> pubgService.getTopNPlayers(
                PlatformRegion.PC_AS,
                Platform.STEAM,
                GameMode.SQUAD,
                HOME_LEADERBOARD_TOTAL_NUMBER
        );
        Callable<List<PlayerDto>> kakaoTask = () -> pubgService.getTopNPlayers(
                PlatformRegion.PC_KAKAO,
                Platform.KAKAO,
                GameMode.SQUAD,
                HOME_LEADERBOARD_TOTAL_NUMBER
        );
        List<Future<List<PlayerDto>>> futures = null;
        try {
            List<Callable<List<PlayerDto>>> tasks = List.of(steamTask, kakaoTask);
            futures = executorService.invokeAll(tasks);
            // 결과를 모델에 담기
            model.addAttribute("steamPlayerList", futures.get(0).get());
            model.addAttribute("kakaoPlayerList", futures.get(1).get());
        } catch (InterruptedException | ExecutionException e) {
            log.error("Error Fetching PUBG Player Data", e);
            model.addAttribute("steamPlayerList", List.of());
            model.addAttribute("kakaoPlayerList", List.of());
        } finally {
            executorService.shutdown();
        }
        return "index";
    }

    @GetMapping("/leaderboard/{platform}")
    public String leaderboard(@PathVariable("platform") String platform, Model model) {
        List<PlayerDto> playerList = null;
        List<PlayerDto> playerFppList = null;
        try {
            PlatformRegion currentPlatformRegion = null;
            switch (Platform.fromLabel(platform)) {
                case KAKAO -> {
                    currentPlatformRegion = PlatformRegion.PC_KAKAO;
                }
                case STEAM -> {
                    currentPlatformRegion = PlatformRegion.PC_AS;
                }
            }
            playerList = pubgService.getTopNPlayers(
                    currentPlatformRegion,
                    Platform.fromLabel(platform),
                    GameMode.SQUAD,
                    LEADERBOARD_TOTAL_NUMBER
            );
            playerFppList = pubgService.getTopNPlayers(
                    currentPlatformRegion,
                    Platform.fromLabel(platform),
                    GameMode.SQUAD_FPP,
                    LEADERBOARD_TOTAL_NUMBER
            );
        } catch (Exception e) {
            log.error("error: {}", e.getMessage());
            playerList = List.of();
        } finally {
            model.addAttribute("playerList", playerList);
            model.addAttribute("playerFppList", playerFppList);
        }
        return "leaderboard/index";
    }

}
