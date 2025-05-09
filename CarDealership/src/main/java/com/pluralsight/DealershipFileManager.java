package com.pluralsight;

import java.io.*;

public class DealershipFileManager {
    private final String filePath = "src/main/resources/inventory.csv"; // File path

    // Load dealership and vehicles from file
    public Dealership getDealership() {
        Dealership dealership = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = reader.readLine(); // Dealership info
            if (line != null) {
                String[] d = line.split("\\|");
                dealership = new Dealership(d[0], d[1], d[2]);
                while ((line = reader.readLine()) != null) {
                    String[] v = line.split("\\|");
                    Vehicle vehicle = new Vehicle(
                            Integer.parseInt(v[0]),
                            Integer.parseInt(v[1]),
                            v[2],
                            v[3],
                            v[4],
                            v[5],
                            Integer.parseInt(v[6]),
                            Double.parseDouble(v[7])
                    );
                    dealership.addVehicle(vehicle);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return dealership;
    }

    // Save dealership and vehicles to file
    public void saveDealership(Dealership dealership) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            // Write dealership info
            writer.println(dealership.getName() + "|" + dealership.getAddress() + "|" + dealership.getPhone());
            // Write vehicles
            for (Vehicle v : dealership.getAllVehicles()) {
                writer.printf("%d|%d|%s|%s|%s|%s|%d|%.2f\n",
                        v.getVin(), v.getYear(), v.getMake(), v.getModel(),
                        v.getType(), v.getColor(), v.getOdometer(), v.getPrice());
            }
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }
}