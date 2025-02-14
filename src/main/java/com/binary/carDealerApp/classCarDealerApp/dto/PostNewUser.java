package com.binary.carDealerApp.classCarDealerApp.dto;

/**
 * Data Transfer Object (DTO) for creating a new user.
 * This record encapsulates the data required to create a new user account.
 *
 * Key points:
 * 1. Uses Java 16+ record feature for concise immutable data holding.
 * 2. Contains essential fields for user creation: username, password, and role.
 * 3. Automatically generates constructor, getters, equals(), hashCode(), and toString() methods.
 * 4. Used to transfer new user data from client to server, typically in user registration process.
 *
 * @param username The desired username for the new user account.
 * @param password The password for the new user account. Note: This should be handled securely.
 * @param role The role assigned to the new user (e.g., "USER", "DEALER").
 */
public record PostNewUser(
        String username,
        String password,
        String role
) {
    // The record provides implicit implementations for constructor, accessors, and utility methods.
}