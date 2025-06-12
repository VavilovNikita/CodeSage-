package com.example.codesage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
class ReviewController {

    @GetMapping("/")
    public String showForm() {
        return "reviewForm";
    }

    @PostMapping("/submitReview")
    public String submitReview(
        @RequestParam("codeAndLogs") String codeAndLogs,
        @RequestParam("pullRequestUrl") String pullRequestUrl,
        Model model) {

        System.out.println("Code/Logs: " + codeAndLogs);
        System.out.println("Pull Request URL: " + pullRequestUrl);

        model.addAttribute("message", "Данные успешно отправлены!");
        return "reviewForm";
    }
}
