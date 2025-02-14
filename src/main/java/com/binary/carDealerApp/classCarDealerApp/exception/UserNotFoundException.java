package com.binary.carDealerApp.classCarDealerApp.exception;

/**
 * UserNotFoundException Class
 *
 * This class represents a custom exception that is thrown when a requested user
 * is not found in the system.
 *
 * Key Points:
 * 1. Custom Exception: Extends RuntimeException to create a specific exception
 *    for user-not-found scenarios.
 * 2. Unchecked Exception: As a RuntimeException, it doesn't require explicit
 *    handling or declaration.
 * 3. Message Propagation: Allows passing a custom error message to the parent
 *    RuntimeException.
 *
 * Usage:
 * This exception is typically thrown in service layer methods when a user
 * lookup operation fails to find a user with the given identifier.
 */
public class UserNotFoundException extends RuntimeException {

    /**
     * Constructs a new UserNotFoundException with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval
     *        by the Throwable.getMessage() method).
     */
    public UserNotFoundException(String message) {
        super(message);
    }
}