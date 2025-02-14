package com.binary.carDealerApp.classCarDealerApp.entities;

import jakarta.persistence.*;

/**
 * Car Entity Class
 *
 * This class represents a Car entity in the car dealer application.
 * It is mapped to a database table using JPA annotations.
 *
 * Key Points:
 * 1. Entity Mapping: Uses @Entity annotation to mark it as a JPA entity.
 * 2. Table Mapping: By default, maps to a table named "Car" in the database.
 * 3. Relationships: Has a Many-to-One relationship with the Dealer entity.
 * 4. ID Generation: Uses auto-generated ID strategy.
 * 5. Custom Column Mapping: Price is mapped to a custom column name.
 */
@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String brand, model, color, regNumber, imageUrl;
    private int year;

    @Column(name = "car_price", nullable = false)
    private double price;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dealer")
    private Dealer dealer;

    /**
     * Default constructor for JPA
     */
    public Car() {
    }

    /**
     * Parameterized constructor to create a Car instance with all fields
     *
     * @param brand The brand of the car
     * @param model The model of the car
     * @param color The color of the car
     * @param regNumber The registration number of the car
     * @param imageUrl The URL of the car's image
     * @param year The manufacturing year of the car
     * @param price The price of the car
     * @param dealer The dealer associated with this car
     */
    public Car(String brand, String model, String color, String regNumber, String imageUrl, int year, double price, Dealer dealer) {
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.regNumber = regNumber;
        this.imageUrl = imageUrl;
        this.year = year;
        this.price = price;
        this.dealer = dealer;
    }

    // Getters and Setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public void setDealer(Dealer dealer) {
        this.dealer = dealer;
    }

    /**
     * Overridden toString method to provide a string representation of the Car object
     *
     * @return A string representation of the Car object
     */
    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", regNumber='" + regNumber + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", year=" + year +
                ", price=" + price +
                ", dealer=" + dealer +
                '}';
    }
}