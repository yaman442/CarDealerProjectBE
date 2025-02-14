package com.binary.carDealerApp.classCarDealerApp.exception;

/**
 * InvalidRoleException Class
 *
 * This class represents a custom exception that is thrown when an invalid role
 * is encountered in the system.
 *
 * Key Points:
 * 1. Custom Exception: Extends RuntimeException to create a specific exception
 *    for invalid role scenarios.
 * 2. Unchecked Exception: As a RuntimeException, it doesn't require explicit
 *    handling or declaration.
 * 3. Message Propagation: Allows passing a custom error message to the parent
 *    RuntimeException.
 *
 * Usage:
 * This exception is typically thrown in service or security layer methods when
 * an operation encounters an invalid or unauthorized role.
 */
public class InvalidRoleException extends RuntimeException {

    /**
     * Constructs a new InvalidRoleException with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval
     *        by the Throwable.getMessage() method).
     */
    public InvalidRoleException(String message) {
        super(message);
    }
}