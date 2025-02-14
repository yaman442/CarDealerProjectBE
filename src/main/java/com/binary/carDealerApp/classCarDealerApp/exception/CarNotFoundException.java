package com.binary.carDealerApp.classCarDealerApp.exception;

/**
 * CarNotFoundException Class
 *
 * This class represents a custom exception that is thrown when a requested car
 * is not found in the system.
 *
 * Key Points:
 * 1. Custom Exception: Extends RuntimeException to create a specific exception
 *    for car-not-found scenarios.
 * 2. Unchecked Exception: As a RuntimeException, it doesn't require explicit
 *    handling or declaration.
 * 3. Message Propagation: Allows passing a custom error message to the parent
 *    RuntimeException.
 *
 * Usage:
 * This exception is typically thrown in service layer methods when a car
 * lookup operation fails to find a car with the given identifier.
 */
public class CarNotFoundException extends RuntimeException {

    /**
     * Constructs a new CarNotFoundException with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval
     *        by the Throwable.getMessage() method).
     */
    public CarNotFoundException(String message) {
        super(message);
    }
}