package com.pluralsight;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DealershipFileManager {

    public Dealership getDealership() {
        Dealership dealership = null;
        try (BufferedReader reader = new BufferedReader(new FileReader("src/resources/inventory.csv"))) {
            String line = reader.readLine(); // First line: dealership info
            if (line != null) {
                String[] dealershipData = line.split("\\|");
                dealership = new Dealership(dealershipData[0], dealershipData[1], dealershipData[2]);

                // Read vehicle lines
                while ((line = reader.readLine()) != null) {
                    String[] vehicleData = line.split("\\|");
                    int vin = Integer.parseInt(vehicleData[0]);
                    int year = Integer.parseInt(vehicleData[1]);
                    String make = vehicleData[2];
                    String model = vehicleData[3];
                    String type = vehicleData[4];
                    String color = vehicleData[5];
                    int odometer = Integer.parseInt(vehicleData[6]);
                    double price = Double.parseDouble(vehicleData[7]);

                    Vehicle vehicle = new Vehicle(vin, year, make, model, type, color, odometer, price);
                    dealership.addVehicle(vehicle);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading dealership file: " + e.getMessage());
        }

        return dealership;
    }

    public void saveDealership(Dealership dealership) {
        // Leave empty for now (you’ll complete this in Phase 5)
    }
}
