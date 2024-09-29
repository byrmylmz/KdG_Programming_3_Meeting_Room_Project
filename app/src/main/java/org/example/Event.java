package org.example;

import java.time.LocalDate;

public class Event {
    private final int eventID;
    private final String name;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String organizer;

    public Event(int eventID, String name, LocalDate startDate, LocalDate endDate, String organizer) {
        this.eventID = eventID;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.organizer = organizer;
    }

    // Getters
    public int getEventID() { return eventID; }
    public String getName() { return name; }
    public LocalDate getStartDate() { return startDate; }
    public LocalDate getEndDate() { return endDate; }
    public String getOrganizer() { return organizer; }

    @Override
    public String toString() {
        return "Event: " + name + ", Organizer: " + organizer + ", Start: " + startDate + ", End: " + endDate;
    }
}
