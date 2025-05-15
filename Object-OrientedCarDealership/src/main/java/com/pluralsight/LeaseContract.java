package com.pluralsight;

public class LeaseContract extends Contract {

    // Constructor
    public LeaseContract(String date, String customerName, String customerEmail, Vehicle vehicleSold) {
        super(date, customerName, customerEmail, vehicleSold); // Call base constructor
    }

    // Total price for lease = expected ending value + lease fee
    @Override
    public double getTotalPrice() {
        double price = vehicleSold.getPrice();
        double endingValue = price * 0.50; // 50% expected value
        double leaseFee = price * 0.07; // 7% lease fee
        return endingValue + leaseFee;
    }

    // Monthly payment for 36-month lease at 4.0% interest
    @Override
    public double getMonthlyPayment() {
        double total = getTotalPrice();
        double rate = 0.04 / 12.0; // Monthly interest
        int months = 36; // 36-month lease

        return (total * rate) / (1 - Math.pow(1 + rate, -months));
    }
}