# Car Dealership Application (Java)
Workbook 4: Workshop - An Object-Oriented Car Dealership

<p align="center"> <pre> About CarDealership </pre> 

A fully functional console-based Java application that simulates a car dealership's vehicle inventory system. Users can:

Search vehicles by price, year, type, etc.

Add or remove vehicles from inventory

View all vehicles

Save changes persistently using a CSV file

<p align="center"> <pre> Features  </pre> 

List all vehicles

Find vehicles by:

Price range

Make and model

Year range

Color

Mileage range

Vehicle type (car, SUV, truck, van, etc.)

Add a new vehicle
Remove a vehicle by VIN
Save all changes to a file
Load dealership and vehicles from a pipe-delimited file

<p align="center"> <pre> Screenshots  </pre>

Home: [home](src/main/resources/Screenshots/Home.png)

Products: [products](src/main/resources/Screenshots/Products.png)

Error: [error](src/main/resources/Screenshots/Error.png)

<p align="center"> <pre> Interesting Code Snippet  </pre>

public void removeVehicle(int vin) {
inventory.removeIf(v -> v.getVin() == vin);
}

// Removes matching VIN

<p align="center"> <pre> Author & Contributors </pre>

Berkcan Emre: https://github.com/berkcanemre

Pinar Durmaz: https://github.com/pinardurmaz

<p align="center"> <pre> License </pre>

This project is open source and available under the MIT License.