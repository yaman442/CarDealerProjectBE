package com.binary.carDealerApp.classCarDealerApp.exception;

import java.time.LocalDateTime;

/**
 * ApiError Record
 *
 * This record represents a standardized error response format for API exceptions.
 * It encapsulates essential information about an error that occurred during API processing.
 *
 * Key Points:
 * 1. Record Usage: Utilizes Java 14+ record feature, providing a concise way to create
 *    an immutable data carrier with automatic getter methods and a concise constructor.
 *
 * 2. Fields:
 *    - message: A descriptive error message.
 *    - statusCode: The HTTP status code associated with the error.
 *    - path: The API endpoint path where the error occurred.
 *    - timestamp: The date and time when the error occurred.
 *
 * 3. Immutability: As a record, ApiError is implicitly immutable, ensuring thread-safety
 *    and preventing accidental modifications after creation.
 *
 * 4. Automatic Methods: The record automatically generates equals(), hashCode(), and
 *    toString() methods, as well as accessor methods for each component.
 *
 * 5. Serialization: This structure is ideal for JSON serialization in API responses,
 *    providing a consistent error format across the application.
 *
 * Usage:
 * Typically used in exception handlers or controller advice to create a standardized
 * error response. It can be easily converted to JSON and sent as the body of an
 * HTTP response when an exception occurs during API processing.
 */
public record ApiError(
        String message,
        int statusCode,
        String path,
        LocalDateTime timestamp
) {
    // No additional methods or constructors are needed.
    // The record provides a compact constructor and accessor methods automatically.
}
