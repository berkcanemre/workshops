package com.pluralsight;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ContractFileManager {
    private final String filePath = "src/main/resources/contracts.csv"; // Output file

    // Appends contract data to file
    public void saveContract(Contract contract) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath, true))) {
            Vehicle v = contract.getVehicleSold(); // Get vehicle info

            // Start with contract type
            if (contract instanceof SalesContract) {
                SalesContract sc = (SalesContract) contract; // Cast
                writer.printf("SALE|%s|%s|%s|%d|%d|%s|%s|%s|%s|%d|%.2f|%.2f|100.00|%.2f|%.2f|%s|%.2f\n",
                        sc.getDate(), sc.getCustomerName(), sc.getCustomerEmail(),
                        v.getVin(), v.getYear(), v.getMake(), v.getModel(), v.getType(), v.getColor(),
                        v.getOdometer(), v.getPrice(),
                        v.getPrice() * 0.05, // Sales tax
                        v.getPrice() < 10000 ? 295.0 : 495.0, // Processing fee
                        sc.getTotalPrice(),
                        sc.isFinanced() ? "YES" : "NO",
                        sc.getMonthlyPayment());
            } else if (contract instanceof LeaseContract) {
                LeaseContract lc = (LeaseContract) contract;
                double expectedValue = v.getPrice() * 0.5;
                double leaseFee = v.getPrice() * 0.07;
                writer.printf("LEASE|%s|%s|%s|%d|%d|%s|%s|%s|%s|%d|%.2f|%.2f|%.2f|%.2f|%.2f\n",
                        lc.getDate(), lc.getCustomerName(), lc.getCustomerEmail(),
                        v.getVin(), v.getYear(), v.getMake(), v.getModel(), v.getType(), v.getColor(),
                        v.getOdometer(), v.getPrice(),
                        expectedValue, leaseFee, lc.getTotalPrice(), lc.getMonthlyPayment());
            }
        } catch (IOException e) {
            System.out.println("Error writing contract file: " + e.getMessage());
        }
    }
}