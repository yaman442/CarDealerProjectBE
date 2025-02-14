package com.binary.carDealerApp.classCarDealerApp.dto;

/**
 * Data Transfer Object (DTO) for User information.
 * This class is used to transfer user data between different layers of the application,
 * particularly when returning user information to clients.
 *
 * Key points:
 * 1. Immutable design with final fields and no setters.
 * 2. Contains only essential user information (username and role).
 * 3. Used to decouple the internal representation of user data from its external representation.
 */
public class UserDto {
    private final String username;
    private final String role;

    /**
     * Constructs a new UserDto with the specified username and role.
     *
     * @param username The username of the user.
     * @param role The role of the user (e.g., "USER", "DEALER").
     */
    public UserDto(String username, String role) {
        this.username = username;
        this.role = role;
    }

    /**
     * Retrieves the username of the user.
     *
     * @return The username as a String.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Retrieves the role of the user.
     *
     * @return The role as a String.
     */
    public String getRole() {
        return role;
    }
}
