package com.pluralsight;

public class Product {
    private String sku;  // SKU (unique identifier for the product)
    private String name; // Name of the product
    private double price; // Price of the product
    private String department; // Department in which the product belongs

    public Product(String sku, String name, double price, String department) {
        this.sku = sku;
        this.name = name;
        this.price = price;
        this.department = department;
    }

    public String getSku() {
        return sku;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getDepartment() {
        return department;
    }

    public void display() {
        System.out.printf("SKU: %s | Name: %s | Price: $%.2f | Department: %s\n",
                sku, name, price, department);  // Display the product details
    }
}
