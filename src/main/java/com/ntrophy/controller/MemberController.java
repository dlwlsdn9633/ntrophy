package com.ntrophy.controller;

import com.ntrophy.domain.member.*;
import com.ntrophy.dto.pubg.enums.Platform;
import com.ntrophy.dto.pubg.enums.PlatformRegion;
import com.ntrophy.dto.pubg.player.PlayerDto;
import com.ntrophy.service.MatchService;
import com.ntrophy.service.PubgService;
import com.ntrophy.service.SeasonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Member;
import java.util.List;
import java.util.concurrent.*;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    //account.14495d5acf2a4d91857bc8b4b50f0bad
    private final PubgService pubgService;
    private final MatchService matchService;
    private final SeasonService seasonService;
    private final ConversionService conversionService;
    @GetMapping("/login")
    public String loginForm() {
        return "member/login";
    }

    @GetMapping({"/record/{accountId}/{platform}", "/record/{accountId}/{platform}/{seasonId}"})
    public String recordForm(
            @PathVariable("accountId") String accountId,
            @PathVariable("platform") String platformLabel,
            @PathVariable(value = "seasonId", required = false) String seasonId,
            Model model
    ) {
        Platform platform = Platform.fromLabel(platformLabel);
        if (seasonId == null) {
            seasonId = pubgService.getCurrentSeasonId(platform);
        }

        ExecutorService executor = Executors.newFixedThreadPool(4);
        try {
            String finalSeasonId = seasonId;
            Callable<PlayerDto> playerRankTask = () -> pubgService.getPlayerRank(platform, accountId, finalSeasonId);
            Callable<PlayerDto> playerNormalTask = () -> pubgService.getPlayerNormal(platform, accountId, finalSeasonId);
            Callable<PlayerDto> playerInfoTask = () -> pubgService.getPlayer(platform, accountId);

            Future<PlayerDto> playerRankFuture = executor.submit(playerRankTask);
            Future<PlayerDto> playerNormalFuture = executor.submit(playerNormalTask);
            Future<PlayerDto> playerInfoFuture = executor.submit(playerInfoTask);

            PlayerDto playerRank = playerRankFuture.get();
            PlayerDto player = playerNormalFuture.get();
            PlayerDto playerInfo = playerInfoFuture.get();

            Player member = Player.builder()
                    .name(playerInfo.getAttributes().getName())
                    .playerRankSquad(conversionService.convert(playerRank, PlayerRankSquad.class))
                    .playerSquad(conversionService.convert(player, PlayerSquad.class))
                    .playerDuo(conversionService.convert(player, PlayerDuo.class))
                    .playerSolo(conversionService.convert(player, PlayerSolo.class))
                    .build();
            model.addAttribute("player", member);
            model.addAttribute("matchList", matchService.getMatches(platform, accountId));
            model.addAttribute("seasonList", seasonService.getSeasonList(platform));
            return "record/index";

        } catch (InterruptedException | ExecutionException e) {
            log.error(e.getMessage());
            model.addAttribute("player", Player.builder().build());
            model.addAttribute("matchList", List.of());
            model.addAttribute("seasonList", List.of());
            return "error/5xx";
        } finally {
            executor.shutdown();
        }
    }
}
