package com.pluralsight;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class OnlineStore {
    public static void main(String[] args) {
        // Map to store products by their SKU (unique identifier)
        Map<String, Product> productMap = new HashMap<>();

        // Map to store cart items and their quantities
        Map<String, Integer> cart = new HashMap<>();
        Scanner scanner = new Scanner(System.in);

        try {
            // Reading product data from a CSV file
            File file = new File("src/main/resources/products.csv");
            Scanner fileScanner = new Scanner(file);
            fileScanner.nextLine(); // skip header row

            // Populating the productMap with product details from CSV
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split("\\|");

                String sku = parts[0];
                String name = parts[1];
                double price = Double.parseDouble(parts[2]);
                String department = parts[3];

                Product product = new Product(sku, name, price, department);
                productMap.put(sku, product);  // Add product to the productMap
            }

            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("CSV file not found.");  // Error handling if the file is missing
            return;
        }

        boolean running = true;  // Flag to keep the program running until the user exits
        while (running) {
            // Displaying the main menu for user interaction
            System.out.println("\n--- MAIN MENU ---");
            System.out.println("1. Show Products");
            System.out.println("2. Show Cart");
            System.out.println("3. Check Out");
            System.out.println("4. Exit");
            System.out.print("Your choice: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":  // Show available products
                    System.out.println("\n--- PRODUCTS ---");
                    // Display each product's details
                    for (Product product : productMap.values()) {
                        product.display();
                    }

                    System.out.print("Enter SKU to add to cart or 'back' to return: ");
                    String skuInput = scanner.nextLine().trim();  // User input for SKU
                    if (!skuInput.equalsIgnoreCase("back")) {
                        Product selected = productMap.get(skuInput.toUpperCase());  // Get product by SKU
                        if (selected != null) {
                            // Add the selected product to the cart, increasing quantity if already added
                            cart.put(skuInput.toUpperCase(), cart.getOrDefault(skuInput.toUpperCase(), 0) + 1);
                            System.out.println(selected.getName() + " added to cart.");
                        } else {
                            System.out.println("Product not found.");
                        }
                    }
                    break;

                case "2":  // Show the current cart
                    if (cart.isEmpty()) {
                        System.out.println("Your cart is empty.");
                        break;
                    }

                    System.out.println("\n--- YOUR CART ---");
                    double total = 0.0;
                    // Display each item in the cart along with its quantity and total price
                    for (String sku : cart.keySet()) {
                        Product p = productMap.get(sku);
                        int quantity = cart.get(sku);
                        double itemTotal = p.getPrice() * quantity;
                        System.out.printf("%s x%d - $%.2f\n", p.getName(), quantity, itemTotal);
                        total += itemTotal;  // Sum the total price of items in the cart
                    }
                    System.out.printf("Total: $%.2f\n", total);

                    System.out.println("1. Remove an item");
                    System.out.println("2. Check Out");
                    System.out.println("3. Back to Main Menu");
                    System.out.print("Your choice: ");
                    String subChoice = scanner.nextLine();

                    if (subChoice.equals("1")) {  // Remove an item from the cart
                        System.out.print("Enter SKU to remove from cart: ");
                        String removeSku = scanner.nextLine().trim().toUpperCase();
                        if (cart.containsKey(removeSku)) {
                            int currentQty = cart.get(removeSku);
                            if (currentQty > 1) {
                                cart.put(removeSku, currentQty - 1);  // Decrease the quantity by 1
                                System.out.println("One unit removed. Remaining: " + (currentQty - 1));
                            } else {
                                cart.remove(removeSku);  // Remove the product entirely if only one left
                                System.out.println("Product removed from cart.");
                            }
                        } else {
                            System.out.println("Product not found in cart.");
                        }

                    } else if (subChoice.equals("2")) {  // Checkout and finish the transaction
                        // Generate receipt for checkout
                        System.out.println("\n--- RECEIPT ---");
                        System.out.println("Thank you for your purchase!");
                        double receiptTotal = 0.0;
                        for (String sku : cart.keySet()) {
                            Product p = productMap.get(sku);
                            int quantity = cart.get(sku);
                            double itemTotal = p.getPrice() * quantity;
                            System.out.printf("%s x%d - $%.2f\n", p.getName(), quantity, itemTotal);
                            receiptTotal += itemTotal;
                        }
                        System.out.printf("Sales Total: $%.2f\n", receiptTotal);

                        // Prompt user for payment
                        System.out.print("Enter payment amount: $");
                        double payment = scanner.nextDouble();
                        scanner.nextLine();  // Consume newline left-over

                        // Verify payment
                        if (payment >= receiptTotal) {
                            double change = payment - receiptTotal;
                            System.out.printf("Change Given: $%.2f\n", change);

                            // Print receipt with payment and change
                            System.out.println("\n--- SALES RECEIPT ---");
                            System.out.println("Order Date: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                            for (String sku : cart.keySet()) {
                                Product p = productMap.get(sku);
                                int quantity = cart.get(sku);
                                System.out.printf("%s x%d - $%.2f\n", p.getName(), quantity, p.getPrice() * quantity);
                            }
                            System.out.printf("Sales Total: $%.2f\n", receiptTotal);
                            System.out.printf("Amount Paid: $%.2f\n", payment);
                            System.out.printf("Change Given: $%.2f\n", change);

                            // Generate receipt file
                            generateReceiptFile(receiptTotal, payment, change);

                            cart.clear();  // Clear the cart after checkout
                            System.out.println("Thank you for shopping with us!");
                            running = false;  // Exit after checkout
                        } else {
                            System.out.println("Insufficient payment. Please try again.");
                        }

                    }
                    break;

                case "3":  // Exit the program
                    System.out.println("Goodbye!");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        }

        scanner.close();  // Close the scanner to prevent memory leak
    }

    // Method to generate receipt file with timestamp
    private static void generateReceiptFile(double total, double paid, double change) {
        try {
            // Get timestamp for the filename
            String timestamp = new SimpleDateFormat("yyyyMMddHHmm").format(new Date());
            File receiptFile = new File("Receipts/" + timestamp + ".txt");
            receiptFile.getParentFile().mkdirs();  // Create directory if not exists

            // Writing to the receipt file
            FileWriter writer = new FileWriter(receiptFile);
            writer.write("--- SALES RECEIPT ---\n");
            writer.write("Order Date: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "\n");
            writer.write("Sales Total: $" + total + "\n");
            writer.write("Amount Paid: $" + paid + "\n");
            writer.write("Change Given: $" + change + "\n");
            writer.close();  // Close the file writer
            System.out.println("Receipt saved to " + receiptFile.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Error generating receipt file: " + e.getMessage());
        }
    }
}
