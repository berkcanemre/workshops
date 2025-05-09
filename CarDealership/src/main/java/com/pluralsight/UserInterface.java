package com.pluralsight;

import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private Dealership dealership; // Holds the loaded dealership
    private final DealershipFileManager fileManager = new DealershipFileManager(); // File manager

    // Start interaction loop
    public void display() {
        init(); // Load from file
        Scanner scanner = new Scanner(System.in);
        while (true) {
            displayMenu(); // Show menu
            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear newline

            switch (choice) {
                case 1 -> displayVehicles(dealership.getAllVehicles());
                case 2 -> {
                    System.out.print("Min price: ");
                    double min = scanner.nextDouble();
                    System.out.print("Max price: ");
                    double max = scanner.nextDouble();
                    displayVehicles(dealership.getVehiclesByPrice(min, max));
                }
                case 3 -> {
                    System.out.print("Make: ");
                    String make = scanner.nextLine();
                    System.out.print("Model: ");
                    String model = scanner.nextLine();
                    displayVehicles(dealership.getVehiclesByMakeModel(make, model));
                }
                case 4 -> {
                    System.out.print("Min year: ");
                    int min = scanner.nextInt();
                    System.out.print("Max year: ");
                    int max = scanner.nextInt();
                    displayVehicles(dealership.getVehiclesByYear(min, max));
                }
                case 5 -> {
                    System.out.print("Color: ");
                    String color = scanner.nextLine();
                    displayVehicles(dealership.getVehiclesByColor(color));
                }
                case 6 -> {
                    System.out.print("Min mileage: ");
                    int min = scanner.nextInt();
                    System.out.print("Max mileage: ");
                    int max = scanner.nextInt();
                    displayVehicles(dealership.getVehiclesByMileage(min, max));
                }
                case 7 -> {
                    System.out.print("Type: ");
                    String type = scanner.nextLine();
                    displayVehicles(dealership.getVehiclesByType(type));
                }
                case 8 -> {
                    // Add vehicle
                    System.out.print("VIN: "); int vin = scanner.nextInt(); scanner.nextLine();
                    System.out.print("Year: "); int year = scanner.nextInt(); scanner.nextLine();
                    System.out.print("Make: "); String make = scanner.nextLine();
                    System.out.print("Model: "); String model = scanner.nextLine();
                    System.out.print("Type: "); String type = scanner.nextLine();
                    System.out.print("Color: "); String color = scanner.nextLine();
                    System.out.print("Odometer: "); int odo = scanner.nextInt(); scanner.nextLine();
                    System.out.print("Price: "); double price = scanner.nextDouble();

                    Vehicle v = new Vehicle(vin, year, make, model, type, color, odo, price);
                    dealership.addVehicle(v);
                    fileManager.saveDealership(dealership); // Save changes
                    System.out.println("Vehicle added.");
                }
                case 9 -> {
                    System.out.print("Enter VIN to remove: ");
                    int vin = scanner.nextInt();
                    dealership.removeVehicle(vin);
                    fileManager.saveDealership(dealership); // Save changes
                    System.out.println("Vehicle removed.");
                }
                case 99 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    // Initialize dealership data
    private void init() {
        dealership = fileManager.getDealership();
        if (dealership == null) {
            System.out.println("Failed to load dealership.");
            System.exit(1);
        }
    }

    // Show menu
    private void displayMenu() {
        System.out.println("""
                ---------------------------------------------------------------------------------------------------
                ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Car Dealership Menu~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                ---------------------------------------------------------------------------------------------------
                1 - List all vehicles
                2 - Search by price
                3 - Search by make/model
                4 - Search by year range
                5 - Search by color
                6 - Search by mileage range
                7 - Search by type
                8 - Add a vehicle
                9 - Remove a vehicle
                99 - Quit
                """);
    }

    // Display a list of vehicles
    private void displayVehicles(List<Vehicle> vehicles) {
        if (vehicles == null || vehicles.isEmpty()) {
            System.out.println("No vehicles found.");
            return;
        }
        for (Vehicle v : vehicles) {
            System.out.println(v);
        }
    }
}