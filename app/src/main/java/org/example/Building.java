package org.example;

import java.util.ArrayList;
import java.util.List;

public class Building {
    private int buildingID;
    private String name;
    private String address;
    private int numberOfFloors;
    private List<Room> rooms;

    public Building(int buildingID, String name, String address, int numberOfFloors) {
        this.buildingID = buildingID;
        this.name = name;
        this.address = address;
        this.numberOfFloors = numberOfFloors;
        this.rooms = new ArrayList<>();
    }

    public int getBuildingID() { return buildingID; }
    public String getName() { return name; }
    public String getAddress() { return address; }
    public int getNumberOfFloors() { return numberOfFloors; }
    public List<Room> getRooms() { return rooms; }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    @Override
    public String toString() {
        return "Building ID: " + buildingID + ", Name: " + name + ", Address: " + address + ", Floors: " + numberOfFloors;
    }
}
