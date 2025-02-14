package com.binary.carDealerApp.classCarDealerApp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

/**
 * Dealer Entity Class
 *
 * This class represents a Dealer entity in the car dealer application.
 * It is mapped to a database table using JPA annotations.
 *
 * Key Points:
 * 1. Entity Mapping: Uses @Entity annotation to mark it as a JPA entity.
 * 2. Table Mapping: By default, maps to a table named "Dealer" in the database.
 * 3. Relationships: Has a One-to-Many relationship with the Car entity.
 * 4. ID Generation: Uses auto-generated ID strategy.
 * 5. JSON Handling: Uses @JsonIgnore to prevent serialization of the cars list.
 */
@Entity
public class Dealer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
        private long dealerId;

    private String dealerName;

    /**
     * One-to-Many relationship with Car entity.
     * Cascade type ALL ensures all operations are cascaded to associated cars.
     * Fetch type LAZY improves performance by loading cars only when accessed.
     * @JsonIgnore prevents infinite recursion during JSON serialization.
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Car> cars;

    /**
     * Default constructor for JPA
     */
    public Dealer() {
    }

    /**
     * Constructor with dealer name
     *
     * @param dealerName The name of the dealer
     */
    public Dealer(String dealerName) {
        this.dealerName = dealerName;
    }

    /**
     * Full constructor with all fields
     *
     * @param dealerId The ID of the dealer
     * @param dealerName The name of the dealer
     * @param cars The list of cars associated with this dealer
     */
    public Dealer(long dealerId, String dealerName, List<Car> cars) { 
        this.dealerId = dealerId;
        this.dealerName = dealerName;
        this.cars = cars;
    }

    // Getters and Setters

    public long getDealerId() {
        return dealerId;
    }

    public void setDealerId(long dealerId) {
        this.dealerId = dealerId;
    }

    public String getDealerName() {
        return dealerName;
    }

    public void setDealerName(String dealerName) {
        this.dealerName = dealerName;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    /**
     * Overridden toString method to provide a string representation of the Dealer object
     *
     * @return A string representation of the Dealer object
     */
    @Override
    public String toString() {
        return "Dealer{" +
                "dealerId=" + dealerId +
                ", dealerName='" + dealerName + '\'' +
                ", cars=" + cars +
                '}';
    }
}


