package com.ntrophy.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/clan")
public class ClanController {
    @GetMapping("/list")
    public String listForm() {
        return "clan/list/index";
    }
    @GetMapping("/write")
    public String writeForm() {
        return "clan/registration/index";
    }
    @GetMapping("/edit")
    public String editForm() {
        return "clan/edit/index";
    }
    @GetMapping("/record")
    public String recordForm() {
        return "clan/record/index";
    }
    @GetMapping("/record-member")
    public String recordMemberForm() {
        return "clan/record_member/index";
    }
}
