package com.ntrophy.controller;

import com.ntrophy.dto.admin.AdminRequestDto;
import com.ntrophy.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;
    @GetMapping("")
    public String adminForm() {
        return "admin/index";
    }
    @PostMapping("/youtube-urls")
    public String youtubeUrls(@ModelAttribute AdminRequestDto adminRequestDto) {
        boolean result = adminService.updateYoutubeUrls(adminRequestDto);
        return "redirect:/admin";
    }
}
