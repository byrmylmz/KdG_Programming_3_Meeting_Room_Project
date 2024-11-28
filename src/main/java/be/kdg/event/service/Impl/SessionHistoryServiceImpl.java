package be.kdg.event.service.Impl;


import be.kdg.event.model.SessionHistory;
import be.kdg.event.service.SessionHistoryService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class SessionHistoryServiceImpl implements SessionHistoryService {

    public void trackPageVisit(HttpSession session, String pageUrl) {
        List<SessionHistory> sessionHistory = (List<SessionHistory>) session.getAttribute("sessionHistory");
        if (sessionHistory == null) {
            sessionHistory = new ArrayList<>();
            session.setAttribute("sessionHistory", sessionHistory);
        }

        sessionHistory.add(new SessionHistory(pageUrl, LocalDateTime.now()));
    }
}
