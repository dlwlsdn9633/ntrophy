package com.ntrophy.controller;

import com.ntrophy.domain.comment.Comment;
import com.ntrophy.domain.enums.Cmd;
import com.ntrophy.domain.enums.PostType;
import com.ntrophy.domain.post.Post;
import com.ntrophy.dto.comment.CommentRequestDto;
import com.ntrophy.dto.post.PostRequestDto;
import com.ntrophy.service.CommentService;
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
    private final ConversionService conversionService;
    private final PostService postService;
    private final CommentService commentService;

    @GetMapping("")
    public String indexForm(
            @RequestParam(
                    value = "reqPage",
                    required = false,
                    defaultValue = "0"
            ) int reqPage,
            @RequestParam(value = "postType", required = false) String postTypeLabel,
            @RequestParam(value = "stype", required = false, defaultValue = "all") String stype,
            @RequestParam(value = "sval", required = false, defaultValue = "") String sval,
            Model model
    ) {
        PostType postType = PostType.DEFAULT;
        try {
            postType = PostType.fromLabel(postTypeLabel);
        } catch (Exception e) {
            log.warn(e.getMessage());
        }
        List<Post> postList = postService.list(
                PostRequestDto.builder()
                        .startPage(reqPage)
                        .postType(postType)
                        .stype(stype)
                        .sval(sval)
                        .build()
        );
        model.addAttribute("postList", postList);
        model.addAttribute("postType", postType);
        return "notice/index";
    }
    @GetMapping("/view/{id}")
    public String viewForm(@PathVariable("id") int id, Model model) {
        Post readPost = postService.read(PostRequestDto.builder().id(id).build());
        List<Comment> commentList = commentService.list(CommentRequestDto.builder().postId(id).build());
        int commentCount = commentService.countByPostId(CommentRequestDto.builder().postId(id).build());
        List<Post> postList = postService.list(PostRequestDto.builder().postType(readPost.getPostType()).build());

        model.addAttribute("post", readPost);
        model.addAttribute("commentList", commentList);
        model.addAttribute("commentCount", commentCount);
        model.addAttribute("postList", postList);
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
    @GetMapping("/edit")
    public String editForm(
            @RequestParam("id") String id,
            Model model
    ) {
        // TODO: 비밀번호를 입력한 사람만 들어오게 만들기 (아직 구현 X)
        Post post = postService.read(PostRequestDto.builder().id(Integer.parseInt(id)).build());
        log.info("editPost: {}", post);
        if (post != null) {
            model.addAttribute("post", post);

            return "notice/edit";
        }
        return "redirect:/notice";
    }
    @PostMapping("/edit")
    public String edit(@ModelAttribute PostRequestDto postRequestDto) {
        int updateResult = postService.update(postRequestDto);
        if (updateResult > 0) {
            return "redirect:/notice/view/" + postRequestDto.getId();
        }
        return "redirect:/notice/view/" + postRequestDto.getId();
    }
    @GetMapping("/password")
    public String passwordForm(@RequestParam("id") String id, @RequestParam("cmd") String cmd, Model model) {
        try {
            model.addAttribute("id", id);
            model.addAttribute("cmd", cmd);
            return "notice/password";
        } catch (Exception e) {
            return "redirect:/notice";
        }
    }

    @PostMapping("/password")
    public String password(@ModelAttribute PostRequestDto postRequestDto, @RequestParam("cmd") String strCmd) {
        try {
            // check password is correct
            boolean isPasswordCorrect = postService.checkPassword(postRequestDto);
            if (isPasswordCorrect) {
                // then,
                switch (Cmd.fromLabel(strCmd)) {
                    case EDIT -> {
                        return "redirect:/notice/edit?id=" + postRequestDto.getId();
                    }
                    case DELETE -> {
                        int deleteResult = postService.delete(postRequestDto);
                        if (deleteResult > 0) {
                            return "redirect:/notice";
                        }
                        return "redirect:/notice";
                    }
                    default -> {
                        return "redirect:/notice/read?id=" + postRequestDto.getId();
                    }
                }
            }
            return "redirect:/notice/read?id=" + postRequestDto.getId();
        } catch (Exception e) {
            log.error(e.getMessage());
            return "redirect:/notice";
        }
    }


}
