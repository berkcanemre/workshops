package com.pluralsight;

import java.util.ArrayList;
import java.util.List;

public class Dealership {
    private String name;
    private String address;
    private String phone;
    private List<Vehicle> inventory;

    public Dealership(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.inventory = new ArrayList<>();
    }

    public void addVehicle(Vehicle vehicle) {
        inventory.add(vehicle);
    }

    public List<Vehicle> getAllVehicles() {
        return inventory;
    }

    // Placeholder methods
    public List<Vehicle> getVehiclesByPrice(double min, double max) { return null; }
    public List<Vehicle> getVehiclesByMakeModel(String make, String model) { return null; }
    public List<Vehicle> getVehiclesByYear(int min, int max) { return null; }
    public List<Vehicle> getVehiclesByColor(String color) { return null; }
    public List<Vehicle> getVehiclesByMileage(int min, int max) { return null; }
    public List<Vehicle> getVehiclesByType(String type) { return null; }
    public void removeVehicle(Vehicle vehicle) { /* empty */ }

    // Add getters/setters for name, address, phone (not for inventory)
}
