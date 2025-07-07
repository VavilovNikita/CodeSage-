package com.example.codesage.controller;

import com.example.codesage.service.DeepSeekService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
class ReviewController {
    private final DeepSeekService deepSeekService;

    @GetMapping("/")
    public String showForm() {
        return "reviewForm";
    }

    @PostMapping("/submitReview")
    public String submitReview(
        @RequestParam("codeAndLogs") String codeAndLogs,
        @RequestParam("pullRequestUrl") String pullRequestUrl,
        Model model) {

        model.addAttribute("message", deepSeekService.chatCompletionString(codeAndLogs));
        return "reviewForm";
    }
}
