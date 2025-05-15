package com.pluralsight;

import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private Dealership dealership; // Holds the loaded dealership
    private final DealershipFileManager fileManager = new DealershipFileManager(); // File manager

    // Start interaction loop
    public void display() {
        init(); // Load dealership data from file
        Scanner scanner = new Scanner(System.in); // Scanner for user input

        while (true) {
            displayMenu(); // Show main menu
            int choice = scanner.nextInt(); // Get user choice
            scanner.nextLine(); // Clear newline from buffer

            switch (choice) {
                case 1 -> displayVehicles(dealership.getAllVehicles()); // List all vehicles

                case 2 -> { // Search by price range
                    System.out.print("Min price: ");
                    double min = scanner.nextDouble();
                    System.out.print("Max price: ");
                    double max = scanner.nextDouble();
                    displayVehicles(dealership.getVehiclesByPrice(min, max));
                }

                case 3 -> { // Search by make and model
                    System.out.print("Make: ");
                    String make = scanner.nextLine();
                    System.out.print("Model: ");
                    String model = scanner.nextLine();
                    displayVehicles(dealership.getVehiclesByMakeModel(make, model));
                }

                case 4 -> { // Search by year range
                    System.out.print("Min year: ");
                    int min = scanner.nextInt();
                    System.out.print("Max year: ");
                    int max = scanner.nextInt();
                    displayVehicles(dealership.getVehiclesByYear(min, max));
                }

                case 5 -> { // Search by color
                    System.out.print("Color: ");
                    String color = scanner.nextLine();
                    displayVehicles(dealership.getVehiclesByColor(color));
                }

                case 6 -> { // Search by mileage
                    System.out.print("Min mileage: ");
                    int min = scanner.nextInt();
                    System.out.print("Max mileage: ");
                    int max = scanner.nextInt();
                    displayVehicles(dealership.getVehiclesByMileage(min, max));
                }

                case 7 -> { // Search by type
                    System.out.print("Type: ");
                    String type = scanner.nextLine();
                    displayVehicles(dealership.getVehiclesByType(type));
                }

                case 8 -> { // Add a vehicle
                    System.out.print("VIN: ");
                    int vin = scanner.nextInt();
                    scanner.nextLine(); // Clear buffer
                    System.out.print("Year: ");
                    int year = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Make: ");
                    String make = scanner.nextLine();
                    System.out.print("Model: ");
                    String model = scanner.nextLine();
                    System.out.print("Type: ");
                    String type = scanner.nextLine();
                    System.out.print("Color: ");
                    String color = scanner.nextLine();
                    System.out.print("Odometer: ");
                    int odo = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Price: ");
                    double price = scanner.nextDouble();

                    Vehicle v = new Vehicle(vin, year, make, model, type, color, odo, price); // Create new vehicle
                    dealership.addVehicle(v); // Add to inventory
                    fileManager.saveDealership(dealership); // Save inventory to file
                    System.out.println("Vehicle added.");
                }

                case 9 -> { // Remove a vehicle
                    System.out.print("Enter VIN to remove: ");
                    int vin = scanner.nextInt();
                    dealership.removeVehicle(vin); // Remove vehicle
                    fileManager.saveDealership(dealership); // Save changes
                    System.out.println("Vehicle removed.");
                }

                case 10 -> { // SELL or LEASE a vehicle (new feature)
                    System.out.print("Enter VIN of vehicle to sell/lease: ");
                    int vin = scanner.nextInt();
                    scanner.nextLine(); // Clear buffer

                    // Find the vehicle in inventory
                    Vehicle vehicle = dealership.getAllVehicles().stream()
                            .filter(v -> v.getVin() == vin)
                            .findFirst()
                            .orElse(null);

                    if (vehicle == null) {
                        System.out.println("Vehicle not found.");
                        break;
                    }

                    // Collect customer info
                    System.out.print("Customer name: ");
                    String customerName = scanner.nextLine();
                    System.out.print("Customer email: ");
                    String customerEmail = scanner.nextLine();
                    System.out.print("Contract date (YYYYMMDD): ");
                    String date = scanner.nextLine();

                    // Choose contract type
                    System.out.print("Is this a Sale or Lease? (S/L): ");
                    String type = scanner.nextLine().trim().toUpperCase();

                    Contract contract = null; // Will hold the contract instance

                    if (type.equals("S")) { // Sale
                        System.out.print("Finance the vehicle? (yes/no): ");
                        boolean finance = scanner.nextLine().trim().equalsIgnoreCase("yes");
                        contract = new SalesContract(date, customerName, customerEmail, vehicle, finance); // Create sales contract
                    } else if (type.equals("L")) { // Lease
                        int currentYear = java.time.LocalDate.now().getYear(); // Get current year
                        if (vehicle.getYear() < currentYear - 3) {
                            System.out.println("Cannot lease a vehicle over 3 years old.");
                            break;
                        }
                        contract = new LeaseContract(date, customerName, customerEmail, vehicle); // Create lease contract
                    } else {
                        System.out.println("Invalid contract type.");
                        break;
                    }

                    // Save contract and remove vehicle
                    ContractFileManager contractFileManager = new ContractFileManager(); // Create file manager
                    contractFileManager.saveContract(contract); // Save contract to file
                    dealership.removeVehicle(vin); // Remove vehicle from inventory
                    fileManager.saveDealership(dealership); // Save updated inventory
                    System.out.println("Contract saved. Vehicle sold/leased and removed from inventory.");
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
        dealership = fileManager.getDealership(); // Load dealership from file
        if (dealership == null) {
            System.out.println("Failed to load dealership.");
            System.exit(1); // Stop program
        }
    }

    // Show menu options
    private void displayMenu() {
        System.out.println("""
                ---------------------------------------------------------------------------------------------------
                ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Car Dealership Menu~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                ---------------------------------------------------------------------------------------------------
                1  - List all vehicles
                2  - Search by price
                3  - Search by make/model
                4  - Search by year range
                5  - Search by color
                6  - Search by mileage range
                7  - Search by type
                8  - Add a vehicle
                9  - Remove a vehicle
//New Feature   10 - Sell or Lease a vehicle
                99 - Quit
                """);
    }

    // Display a list of vehicles
    private void displayVehicles(List<Vehicle> vehicles) {
        if (vehicles == null || vehicles.isEmpty()) {
            System.out.println("No vehicles found."); // No results
            return;
        }

        // Loop through each vehicle and print
        for (Vehicle v : vehicles) {
            System.out.println(v);
        }
    }
}