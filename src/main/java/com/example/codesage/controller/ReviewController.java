package com.example.codesage.controller;

import com.example.codesage.model.InputMode;
import com.example.codesage.service.DeepSeekService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        @RequestParam("mode") InputMode mode,
        @RequestParam(value = "inputText", required = false) String inputText, Model model) {

        String response = deepSeekService.chatCompletionString(inputText, mode);
        model.addAttribute("message", response);
        return "reviewForm";
    }
}
