package org.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DataFactory {
    public static List<Room> rooms = new ArrayList<>();
    public static List<Event> events = new ArrayList<>();

    public static void seed() {
        Building building1 = new Building(1, "KdG Building", "Brussel Straat", 5);
        Building building2 = new Building(2, "Tech Center KdG", "2000 Antwerp", 3);

        Room room1 = new Room(1, "101", 50, RoomType.CONFERENCE, building1);
        Room room2 = new Room(2, "102", 30, RoomType.CLASSROOM, building1);
        Room room3 = new Room(3, "201", 100, RoomType.AUDITORIUM, building2);
        Room room4 = new Room(4, "202", 20, RoomType.WORKSHOP, building2);

        Event event1 = new Event(1, "AI Tech", LocalDate.of(2024, 10, 1), LocalDate.of(2024, 10, 2), "Bayram Yilmaz");
        Event event2 = new Event(2, "Java Meeting", LocalDate.of(2024, 11, 15), LocalDate.of(2024, 11, 16), "Furkan Yilmaz");

        room1.addEvent(event1);
        room2.addEvent(event2);
        room3.addEvent(event1);

        building1.addRoom(room1);
        building1.addRoom(room2);
        building2.addRoom(room3);
        building2.addRoom(room4);

        rooms.add(room1);
        rooms.add(room2);
        rooms.add(room3);
        rooms.add(room4);

        events.add(event1);
        events.add(event2);
    }
}
