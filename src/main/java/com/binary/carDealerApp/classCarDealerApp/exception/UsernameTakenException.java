package com.binary.carDealerApp.classCarDealerApp.exception;

/**
 * UsernameTakenException Class
 *
 * This class represents a custom exception that is thrown when an attempt is made
 * to create or update a user with a username that already exists in the system.
 *
 * Key Points:
 * 1. Custom Exception: Extends RuntimeException to create a specific exception
 *    for username conflict scenarios.
 * 2. Unchecked Exception: As a RuntimeException, it doesn't require explicit
 *    handling or declaration.
 * 3. Message Propagation: Allows passing a custom error message to the parent
 *    RuntimeException.
 *
 * Usage:
 * This exception is typically thrown in service layer methods during user
 * registration or profile update operations when a username conflict is detected.
 */
public class UsernameTakenException extends RuntimeException {

    /**
     * Constructs a new UsernameTakenException with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval
     *        by the Throwable.getMessage() method).
     */
    public UsernameTakenException(String message) {
        super(message);
    }
}
