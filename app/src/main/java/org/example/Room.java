package org.example;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private int roomID;
    private String roomNumber;
    private double capacity;
    private RoomType type;
    private Building building;
    private List<Event> events;

    public Room(int roomID, String roomNumber, double capacity, RoomType type, Building building) {
        this.roomID = roomID;
        this.roomNumber = roomNumber;
        this.capacity = capacity;
        this.type = type;
        this.building = building;
        this.events = new ArrayList<>();
    }

    public int getRoomID() { return roomID; }
    public String getRoomNumber() { return roomNumber; }
    public double getCapacity() { return capacity; }
    public RoomType getType() { return type; }
    public Building getBuilding() { return building; }
    public List<Event> getEvents() { return events; }

    public void addEvent(Event event) {
        events.add(event);
    }

    @Override
    public String toString() {
        return "Room Number: " + roomNumber + ", Capacity: " + capacity + ", Type: " + type;
    }
}
