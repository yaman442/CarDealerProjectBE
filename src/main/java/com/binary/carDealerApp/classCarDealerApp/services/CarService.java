package com.binary.carDealerApp.classCarDealerApp.services;

import com.binary.carDealerApp.classCarDealerApp.entities.Car;

import java.util.List;

/**
 * CarService Interface
 * 
 * Purpose:
 * - This interface defines the contract for car-related operations in the application.
 * - It provides a clear set of methods that any implementing class must provide.
 * - It helps in achieving loose coupling by allowing different implementations of these methods.
 *
 * Design Principles:
 * - Follows the Single Responsibility Principle by focusing only on car-related operations.
 * - Provides a clear separation between the definition of operations (interface) and their implementation (which will be in a separate class).
 *
 * Usage in Spring:
 * - In a Spring application, this interface would typically be implemented by a service class (e.g., CarServiceImpl).
 * - The implementing class would be annotated with @Service and would contain the actual business logic for these operations.
 */
public interface CarService {

    /**
     * Retrieves all cars in the system.
     * @return A List of all Car objects
     */
    public List<Car> getAllCars();

    /**
     * Creates a new car entry in the system.
     * @param car The Car object to be created
     * @return The created Car object
     */
    public Car createCar(Car car);

    /**
     * Updates an existing car's information.
     * @param car The Car object with updated information
     * @param id The ID of the car to be updated
     * @return The updated Car object
     */
    public Car updateCar(Car car, Long id);

    /**
     * Deletes a car from the system based on its ID.
     * @param id The ID of the car to be deleted
     */
    public void deleteCarById(Long id);

    /**
     * Retrieves a specific car by its ID.
     * @param id The ID of the car to retrieve
     * @return The Car object if found
     */
    public Car getCarById(Long id);
}
