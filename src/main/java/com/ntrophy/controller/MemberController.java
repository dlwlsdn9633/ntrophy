package com.ntrophy.controller;

import com.ntrophy.domain.member.Member;
import com.ntrophy.domain.member.PlayerRankSquad;
import com.ntrophy.dto.pubg.enums.Platform;
import com.ntrophy.dto.pubg.player.PlayerDto;
import com.ntrophy.service.PubgService;
import com.ntrophy.util.Function;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    //account.14495d5acf2a4d91857bc8b4b50f0bad
    private final PubgService pubgService;
    private final ConversionService conversionService;
    @GetMapping("/login")
    public String loginForm() {
        return "member/login";
    }

    @GetMapping("/record/{accountId}")
    public String recordForm(@PathVariable("accountId") String accountId, Model model) {
        PlayerDto player = pubgService.getPlayerBySeasonId(accountId);
        PlayerDto playerInfo = pubgService.getPlayer(accountId);

        Member member = Member.builder()
                .name(playerInfo.getAttributes().getName())
                .playerRankSquad(conversionService.convert(player, PlayerRankSquad.class))
                .build();
        model.addAttribute("player", member);
        return "record/index";
    }
}
