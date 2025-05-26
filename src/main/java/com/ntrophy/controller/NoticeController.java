package com.ntrophy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/notice")
public class NoticeController {
    @GetMapping("")
    public String indexForm() {
        return "notice/index";
    }
    @GetMapping("/view/{no}")
    public String viewForm(@PathVariable("no") int no) {
        return "notice/view";
    }
    @GetMapping("/write")
    public String writeForm() {
        return "notice/write";
    }
    @PostMapping("/write")
    public String write() {
        return "redirect:/notice/view/";
    }
}
