# Car Dealership Application (Java)
Workbook 5: Workshop - An Object-Oriented Car
Dealership System Part-2

<p align="center"> <pre> About CarDealership </pre> 

A fully functional console-based Java application that simulates a car dealership's vehicle inventory system. 

Updated with Object-Oriented Coding  
Users can:
- Search vehicles by price, year, type, mileage, color, and make/model
dd or remove vehicles from inventory

- Save all changes persistently using CSV files

- Record **sales or lease contracts** with accurate price and financing logic

- Persist contracts into a separate contract log file

<p align="center"> <pre> Features  </pre> 

List all vehicles       
Find vehicles by:
- Price range
- Make and model
- Year range
- Color
- Mileage range
- Vehicle type (car, SUV, truck, van, etc.)

Add a new vehicle  
Remove a vehicle by VIN  
Sell or Lease a vehicle  
Save all changes to an inventory file  
Append all sales and leases to a contracts file     
Load dealership and vehicles from a pipe-delimited file

<p align="center"> <pre> Screenshots  </pre>

Home: [home](src/main/resources/Screenshots/Home.png)

Products: [products](src/main/resources/Screenshots/Products.png)

Error: [error](src/main/resources/Screenshots/Error.png)

<p align="center"> <pre> Interesting Code Snippet  </pre>

This code handles both short-term and long-term financing logic dynamically

    public double getMonthlyPayment() {
    if (!finance) return 0.0; // No financing if user selected NO

    double totalPrice = getTotalPrice(); // Final amount including tax and fees
    double interestRate;
    int months;

    // Determine interest and term based on total price
    if (totalPrice >= 10000) {
        interestRate = 0.0425; // 4.25% for loans $10,000+
        months = 48;
    } else {
        interestRate = 0.0525; // 5.25% for smaller loans
        months = 24;
    }

    double monthlyRate = interestRate / 12.0; // Monthly interest rate

    // Amortization formula for equal monthly payments
    return (totalPrice * monthlyRate) / (1 - Math.pow(1 + monthlyRate, -months));
    }

<p align="center"> <pre> Author & Contributors </pre>

Berkcan Emre: https://github.com/berkcanemre

Pinar Durmaz: https://github.com/pinardurmaz

<p align="center"> <pre> License </pre>

This project is open source and available under the MIT License.