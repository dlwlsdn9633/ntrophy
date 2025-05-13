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

import java.util.List;
import java.util.concurrent.*;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {
    private static final int LEADERBOARD_TOTAL_NUMBER = 100;
    private final PubgService pubgService;
    @GetMapping("/")
    public String home(Model model) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Callable<List<PlayerDto>> steamTask = () -> pubgService.getTop10Players(PlatformRegion.PC_AS, Platform.STEAM, GameMode.SQUAD);
        Callable<List<PlayerDto>> kakaoTask = () -> pubgService.getTop10Players(PlatformRegion.PC_KAKAO, Platform.KAKAO, GameMode.SQUAD);

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

    @GetMapping("/leaderboard")
    public String leaderboard(Model model) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        Callable<List<PlayerDto>> steamTask = () -> pubgService.getTopNPlayers(PlatformRegion.PC_AS, Platform.STEAM, GameMode.SQUAD, LEADERBOARD_TOTAL_NUMBER);
        Callable<List<PlayerDto>> kakaoTask = () -> pubgService.getTopNPlayers(PlatformRegion.PC_KAKAO, Platform.KAKAO, GameMode.SQUAD, LEADERBOARD_TOTAL_NUMBER);
        List<Future<List<PlayerDto>>> futures = null;
        try {
            List<Callable<List<PlayerDto>>> tasks = List.of(steamTask, kakaoTask);
            futures = executorService.invokeAll(tasks);

            model.addAttribute("steamPlayerList", futures.get(0).get());
            model.addAttribute("kakaoPlayerList", futures.get(1).get());
        } catch (InterruptedException | ExecutionException e) {
            log.error("Error Fetching PUBG Player Data", e);
            model.addAttribute("steamPlayerList", List.of());
            model.addAttribute("kakaoPlayerList", List.of());
        } finally {
            executorService.shutdown();
        }
        return "leaderboard/index";
    }

}
