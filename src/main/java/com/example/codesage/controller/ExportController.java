package com.example.codesage.controller;

import com.example.codesage.service.GitExportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class ExportController {

    private final GitExportService gitExportService;

    @GetMapping("/export")
    public String showExportForm() {
        return "exportCode";
    }

    @PostMapping("/export")
    public String handleExport(@RequestParam String repoUrl, Model model) {
        String exportedCode = gitExportService.exportCodeFromRepo(repoUrl);
        model.addAttribute("exportedCode", exportedCode);
        return "exportCode";
    }
}
