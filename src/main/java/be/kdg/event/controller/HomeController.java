package be.kdg.event.controller;

import be.kdg.event.service.SessionHistoryService;
import be.kdg.event.service.StatisticsService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    private final StatisticsService statisticsService;
    private final SessionHistoryService sessionHistoryService;

    public HomeController(StatisticsService statisticsService, SessionHistoryService sessionHistoryService) {
        this.statisticsService = statisticsService;
        this.sessionHistoryService = sessionHistoryService;
    }

    @GetMapping("/")
    public String home(Model model, HttpSession session) {
        sessionHistoryService.trackPageVisit(session, "/home");

        List<Long> counts = statisticsService.getTotalCounts();
        model.addAttribute("totalRoomNumber", counts.getFirst());
        model.addAttribute("totalBuildingNumber", counts.get(1));
        model.addAttribute("totalEventNumber", counts.get(2));
        return "index";
    }



}
