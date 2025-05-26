package com.ntrophy.controller;

import com.ntrophy.domain.enums.PostType;
import com.ntrophy.dto.post.PostRequestDto;
import com.ntrophy.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.beans.PropertyEditorSupport;

@Slf4j
@Controller
@RequestMapping("/notice")
@RequiredArgsConstructor
public class NoticeController {
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(PostType.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                setValue(PostType.fromCode(Integer.parseInt(text)));
            }
        });
    }

    private final PostService postService;
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
    public String write(@ModelAttribute PostRequestDto postRequestDto) {
        log.info("postRequestDto: {}", postRequestDto);
        return "redirect:/notice/view/";
    }
}
