package be.kdg.event.service;

import jakarta.servlet.http.HttpSession;

public interface SessionHistoryService {

    public void trackPageVisit(HttpSession session,String pageUrl);
}
