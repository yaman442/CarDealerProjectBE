package com.binary.carDealerApp.classCarDealerApp.services;

import com.binary.carDealerApp.classCarDealerApp.dto.AuthorizationRequest;
import com.binary.carDealerApp.classCarDealerApp.dto.PostNewUser;
import com.binary.carDealerApp.classCarDealerApp.dto.UserDto;

/**
 * UserCredentialService Interface
 *
 * This interface defines the contract for user credential-related operations in the car dealer application.
 * It provides methods for managing user accounts and credentials.
 *
 * Key Points:
 * 1. Service Layer: This interface is part of the service layer, separating business logic from controllers and repositories.
 * 2. DTO Usage: Uses Data Transfer Objects (DTOs) for input and output, promoting loose coupling and data encapsulation.
 * 3. User Creation: Defines a method for creating new user accounts.
 */
public interface UserCredentialService {

    /**
     * Creates a new user account based on the provided user information.
     *
     * @param postNewUser A DTO containing the information required to create a new user account.
     * @return UserDto A DTO containing the details of the newly created user account.
     *
     * Note: The actual implementation of this method should handle:
     * - Validation of user input
     * - Password encryption
     * - Saving the new user to the database
     * - Handling potential conflicts (e.g., username already exists)
     * - Mapping the created user entity to a UserDto for return
     */
    UserDto createUser(PostNewUser postNewUser);
    String login(AuthorizationRequest request);

    // Potential future methods:
    // UserDto getUserById(String id);
    // UserDto updateUser(String id, UpdateUserDto updateUserDto);
    // void deleteUser(String id);
    // List<UserDto> getAllUsers();
    // UserDto getUserByUsername(String username);
}