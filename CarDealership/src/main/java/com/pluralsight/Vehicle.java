package com.pluralsight;

// First setup: created class
public class Vehicle {
    private int vin; // Vehicle ID
    private int year; // Year of manufacture
    private String make; //  Make
    private String model; // Model
    private String type; // Type
    private String color; // Color
    private int odometer; // Mileage
    private double price; // Price

// Phase 1: Added constructor(s), getters, setters, and a toString() method
public Vehicle(int vin, int year, String make, String model, String type, String color, int odometer, double price) {
    this.vin = vin;
    this.year = year;
    this.make = make;
    this.model = model;
    this.type = type;
    this.color = color;
    this.odometer = odometer;
    this.price = price;
    }

    //Phase 3-4: Getters and Setters
    public int getVin() { return vin; }
    public void setVin(int vin) { this.vin = vin; }

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    public String getMake() { return make; }
    public void setMake(String make) { this.make = make; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public int getOdometer() { return odometer; }
    public void setOdometer(int odometer) { this.odometer = odometer; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    // Phase2: String representation for displaying vehicle info
    @Override
    public String toString() {
        return String.format("VIN: %d | Year: %d | %s %s | Type: %s | Color: %s | Odometer: %,d | $%.2f",
                vin, year, make, model, type, color, odometer, price);
    }
}