package be.kdg.event.model;

import java.time.LocalDateTime;

public class SessionHistory {

    private String pageUrl;
    private LocalDateTime timestamp;

    public SessionHistory(String pageUrl, LocalDateTime timestamp) {
        this.pageUrl = pageUrl;
        this.timestamp = timestamp;
    }

    public String getPageUrl() {
        return pageUrl;
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
