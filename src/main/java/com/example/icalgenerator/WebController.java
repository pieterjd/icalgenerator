package com.example.icalgenerator;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;

@Controller
public class WebController {
    private final IcalService icalService;

    public WebController(IcalService icalService) {
        this.icalService = icalService;
    }

    @GetMapping
    public String index(Model model){
        DateFormSubmission submission = new DateFormSubmission();
        model.addAttribute("submission",submission);
        return "index";
    }

    @PostMapping("/generate")
    public void generate(@ModelAttribute DateFormSubmission s, HttpServletResponse response) throws IOException {

        response.getOutputStream().println(icalService.generate(s).toString());
        response.setHeader(HttpHeaders.CONTENT_TYPE, "text/calendar");
        String cleanFileName=s.getDescription().replaceAll("\\W+", "").toLowerCase();
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=\"%s.ics\"", cleanFileName));
    }


}
