package com.ntrophy.controller;

import com.ntrophy.domain.enums.PostType;
import com.ntrophy.domain.post.Post;
import com.ntrophy.dto.post.PostRequestDto;
import com.ntrophy.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.beans.PropertyEditorSupport;
import java.util.List;

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

    private final ConversionService conversionService;
    private final PostService postService;
    @GetMapping({"", "/{postType}"})
    public String indexForm(
            @RequestParam(
                    value = "reqPage",
                    required = false,
                    defaultValue = "0"
            ) int reqPage,
            @PathVariable(value = "postType", required = false) String postType,
            Model model
    ) {
        try {
            List<Post> postList = postService.list(
                    PostRequestDto.builder()
                            .startPage(reqPage)
                            .build()
            );
            model.addAttribute("postList", postList);
            return "notice/index";
        } catch (Exception e) {
            log.error(e.getMessage());
            return "redirect:/notice";
        }
    }
    @GetMapping("/view/{id}")
    public String viewForm(@PathVariable("id") int id, Model model) {
        Post readPost = postService.read(PostRequestDto.builder().id(id).build());
        model.addAttribute("post", readPost);

        return "notice/view";
    }
    @GetMapping("/write")
    public String writeForm() {
        return "notice/write";
    }
    @PostMapping("/write")
    public String write(@ModelAttribute PostRequestDto postRequestDto) {
        Post insertedPost = postService.insert(postRequestDto);
        return "redirect:/notice/view/" + insertedPost.getId();
    }
}
