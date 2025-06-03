package com.ntrophy.controller;

import com.ntrophy.domain.post.Post;
import com.ntrophy.dto.post.PostRequestDto;
import com.ntrophy.dto.pubg.player.PlayerDto;
import com.ntrophy.dto.pubg.enums.GameMode;
import com.ntrophy.dto.pubg.enums.Platform;
import com.ntrophy.dto.pubg.enums.PlatformRegion;
import com.ntrophy.service.AdminService;
import com.ntrophy.service.PostService;
import com.ntrophy.service.PubgService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.concurrent.*;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {
    private static final int LEADERBOARD_TOTAL_NUMBER = 50;
    private static final int HOME_LEADERBOARD_TOTAL_NUMBER = 10;
    private final PubgService pubgService;
    private final PostService postService;
    private final AdminService adminService;
    @GetMapping("/")
    public String home(Model model) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Callable<List<PlayerDto>> steamTask = () -> pubgService.getTopNPlayers(
                PlatformRegion.PC_AS,
                Platform.STEAM,
                GameMode.SQUAD,
                HOME_LEADERBOARD_TOTAL_NUMBER
        );
        Callable<List<PlayerDto>> steamFppTask = () -> pubgService.getTopNPlayers(
                PlatformRegion.PC_AS,
                Platform.STEAM,
                GameMode.SQUAD_FPP,
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
            List<Callable<List<PlayerDto>>> tasks = List.of(steamTask, kakaoTask, steamFppTask);
            futures = executorService.invokeAll(tasks);
            // 결과를 모델에 담기
            model.addAttribute("steamPlayerList", futures.get(0).get());
            model.addAttribute("kakaoPlayerList", futures.get(1).get());
            model.addAttribute("steamFppPlayerList", futures.get(2).get());
            model.addAttribute("postList", postService.list(PostRequestDto.builder().startPage(0).build()));
            model.addAttribute("youtubeList", adminService.youtubeList());

        } catch (InterruptedException | ExecutionException e) {
            log.error("Error Fetching PUBG Player Data", e);
            model.addAttribute("steamPlayerList", List.of());
            model.addAttribute("kakaoPlayerList", List.of());
            model.addAttribute("steamFppPlayerList", List.of());
            model.addAttribute("postList", postService.list(PostRequestDto.builder().startPage(0).build()));
            model.addAttribute("youtubeList", adminService.youtubeList());
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

    @GetMapping("/search")
    public String search(@RequestParam("name") String name) {
        PlayerDto player = pubgService.getPlayerByName(name);
        try {
            Platform platform = Platform.fromLabel(player.getAttributes().getShardId());
            return "redirect:/member/record/" + player.getId() + "/" + platform.getLabel();
        } catch (Exception e) {
            return "redirect:/not-found";
        }
    }
    @GetMapping("/not-found")
    public String notFound() {
        return "record/search";
    }
}
