package com.ntrophy.controller;

import com.ntrophy.dto.pubg.player.PlayerDto;
import com.ntrophy.service.PubgService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    //account.14495d5acf2a4d91857bc8b4b50f0bad
    private final PubgService pubgService;
    @GetMapping("/login")
    public String loginForm() {
        return "member/login";
    }
    @GetMapping("/record")
    public String recordForm(@RequestParam("accountId") String accountId) {

        //log.info("{}", accountId);
        PlayerDto player = pubgService.getPlayer(accountId);
        log.info("{}", player);
        return "record/index";
    }
}
