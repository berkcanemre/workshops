package com.pluralsight;

// Abstract base class for all contract types
public abstract class Contract {
    protected String date; // Contract date as string
    protected String customerName; // Customer full name
    protected String customerEmail; // Customer email address
    protected Vehicle vehicleSold; // Vehicle sold or leased

    // Constructor
    public Contract(String date, String customerName, String customerEmail, Vehicle vehicleSold) {
        this.date = date;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.vehicleSold = vehicleSold;
    }

    // Getters
    public String getDate() { return date; }
    public String getCustomerName() { return customerName; }
    public String getCustomerEmail() { return customerEmail; }
    public Vehicle getVehicleSold() { return vehicleSold; }

    // Abstract methods for subclasses to implement
    public abstract double getTotalPrice(); // Total price of contract
    public abstract double getMonthlyPayment(); // Monthly payment
}