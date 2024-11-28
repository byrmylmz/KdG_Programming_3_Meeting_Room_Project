package be.kdg.event.controller;

import be.kdg.event.model.SessionHistory;
import be.kdg.event.service.SessionHistoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SessionHistoryController {
    private final SessionHistoryService sessionHistoryService;

    public SessionHistoryController(SessionHistoryService sessionHistoryService) {
        this.sessionHistoryService = sessionHistoryService;
    }


    @GetMapping("/session-history")
    public String showSessionHistory(Model model,
                                     @SessionAttribute(value = "sessionHistory", required = false) List<SessionHistory> sessionHistory) {
        if (sessionHistory == null) {
            sessionHistory = new ArrayList<>();
        }
        model.addAttribute("sessionHistory", sessionHistory);
        return "session-history";
    }
}
