package com.pluralsight;

public class SalesContract extends Contract {
    private boolean finance; // Whether financing is chosen

    // Constructor
    public SalesContract(String date, String customerName, String customerEmail,
                         Vehicle vehicleSold, boolean finance) {
        super(date, customerName, customerEmail, vehicleSold); // Call base class constructor
        this.finance = finance; // Save finance choice
    }

    // Return the full sale price including tax and fees
    @Override
    public double getTotalPrice() {
        double price = vehicleSold.getPrice(); // Base vehicle price
        double salesTax = price * 0.05; // 5% sales tax
        double recordingFee = 100.0; // Flat recording fee
        double processingFee = price < 10000 ? 295.0 : 495.0; // Fee based on price

        return price + salesTax + recordingFee + processingFee; // Total
    }

    // Calculate financing monthly payment if applicable
    @Override
    public double getMonthlyPayment() {
        if (!finance) return 0.0; // No financing
        double totalPrice = getTotalPrice();
        double interestRate;
        int months;

        if (totalPrice >= 10000) {
            interestRate = 0.0425; // 4.25% interest for 48 months
            months = 48;
        } else {
            interestRate = 0.0525; // 5.25% interest for 24 months
            months = 24;
        }

        double monthlyRate = interestRate / 12.0;
        return (totalPrice * monthlyRate) / (1 - Math.pow(1 + monthlyRate, -months));
    }

    public boolean isFinanced() { return finance; }
}