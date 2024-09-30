package org.example.eventmanagement.presentation;


import org.example.eventmanagement.model.Building;
import org.example.eventmanagement.model.Event;
import org.example.eventmanagement.model.Room;
import org.example.eventmanagement.service.BuildingService;
import org.example.eventmanagement.service.EventService;
import org.example.eventmanagement.service.RoomService;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
@Component
public class Menu {
    private final BuildingService buildingService;
    private final RoomService roomService;
    private final EventService eventService;
    private final Scanner scanner;

    public Menu(BuildingService buildingService, RoomService roomService, EventService eventService) {
        this.buildingService = buildingService;
        this.roomService = roomService;
        this.eventService = eventService;
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        while (true) {
            System.out.println("\nWhat would you like to do?");
            System.out.println("1) Add building");
            System.out.println("2) List all buildings");
            System.out.println("3) Add room");
            System.out.println("4) List all rooms");
            System.out.println("5) Add event");
            System.out.println("6) List all events");
            System.out.println("0) Quit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1 -> addBuilding();
                case 2 -> listBuildings();
                case 3 -> addRoom();
                case 4 -> listRooms();
                case 5 -> addEvent();
                case 6 -> listEvents();
                case 0 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice, please try again.");
            }
        }
    }

    private void addBuilding() {
        System.out.println("Enter building name: ");
        String name = scanner.nextLine();
        System.out.println("Enter building address: ");
        String address = scanner.nextLine();
        System.out.println("Enter number of floors: ");
        int numberOfFloors = scanner.nextInt();
        scanner.nextLine();

        Building building = new Building();
        building.setName(name);
        building.setAddress(address);
        building.setNumberOfFloors(numberOfFloors);
        buildingService.saveBuilding(building);
        System.out.println("Building added successfully.");
    }

    private void listBuildings() {
        System.out.println("All Buildings:");
        buildingService.getAllBuildings().forEach(System.out::println);
    }

    private void addRoom() {
        System.out.println("Enter room number: ");
        String roomNumber = scanner.nextLine();
        System.out.println("Enter room capacity: ");
        int capacity = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter associated building ID: ");
        Long buildingID = scanner.nextLong();
        scanner.nextLine();


        Building building = buildingService.getBuildingById(buildingID);
        if (building == null) {
            System.out.println("Building not found!");
            return;
        }

        Room room = new Room();
        room.setRoomNumber(roomNumber);
        room.setCapacity(capacity);
        room.setBuilding(building);
        roomService.saveRoom(room); // Save the Room object
        System.out.println("Room added successfully.");


    }

    private void listRooms() {
        System.out.println("All Rooms:");
        roomService.getAllRooms().forEach(System.out::println);
    }

    private void addEvent() {
        System.out.print("Enter event name: ");
        String eventName = scanner.nextLine();
        System.out.print("Enter start date and time (yyyy-MM-dd HH:mm): ");
        String startDateTimeString = scanner.nextLine();
        System.out.print("Enter end date and time (yyyy-MM-dd HH:mm): ");
        String endDateTimeString = scanner.nextLine();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime startDateTime = LocalDateTime.parse(startDateTimeString, formatter);
        LocalDateTime endDateTime = LocalDateTime.parse(endDateTimeString, formatter);

        Event event = new Event();
        event.setEventName(eventName);
        event.setStartDateTime(startDateTime);
        event.setEndDateTime(endDateTime);
        eventService.saveEvent(event);
        System.out.println("Event added successfully.");

    }

    private void listEvents() {
        System.out.println("All Events:");
        eventService.getAllEvents().forEach(System.out::println);
    }
}
