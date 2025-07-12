package com.example.codesage.controller;

import com.example.codesage.model.InputMode;
import com.example.codesage.model.Recommendation;
import com.example.codesage.model.RequestedData;
import com.example.codesage.service.DeepSeekService;
import com.example.codesage.service.RequestService;
import com.example.codesage.service.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Controller
@RequiredArgsConstructor
class ReviewController {
    private final DeepSeekService deepSeekService;
    private final ResponseService responseService;
    private final RequestService requestService;

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

    @GetMapping("/reviewHistory")
    public String getReviewHistory(Model model) {
        List<Recommendation> recommendations = responseService.findAll();
        model.addAttribute("recommendations", recommendations);
        return "reviewHistory";
    }

    @GetMapping("/review/{id}")
    public String viewRequestDetails(@PathVariable Long id, Model model) {
        RequestedData data = requestService.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Запрос не найден"));

        model.addAttribute("requestedData", data);
        return "reviewDetails";
    }

}
