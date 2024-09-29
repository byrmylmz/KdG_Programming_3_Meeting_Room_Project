package org.example;

// Main.java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DataFactory.seed();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nWhat would you like to do?");
            System.out.println("0) Quit");
            System.out.println("1) Show all buildings");
            System.out.println("2) Show rooms with specified capacity");
            System.out.println("3) Show all events");
            System.out.println("4) Show events by organizer");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("\nAll Buildings:");
                    for (Room room : DataFactory.rooms) {
                        System.out.println(room.getBuilding());
                    }
                    break;

                case 2:
                    System.out.print("Enter minimum room capacity: ");
                    double capacity = scanner.nextDouble();
                    for (Room room : DataFactory.rooms) {
                        if (room.getCapacity() >= capacity) {
                            System.out.println(room);
                        }
                    }
                    break;

                case 3:
                    System.out.println("\nAll Events:");
                    for (Event event : DataFactory.events) {
                        System.out.println(event);
                    }
                    break;

                case 4:
                    System.out.print("Enter organizer name or leave blank: ");
                    String organizer = scanner.nextLine();
                    for (Event event : DataFactory.events) {
                        if (event.getOrganizer().contains(organizer)) {
                            System.out.println(event);
                        }
                    }
                    break;

                case 0:
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        } while (choice != 0);

        scanner.close();
    }
}
