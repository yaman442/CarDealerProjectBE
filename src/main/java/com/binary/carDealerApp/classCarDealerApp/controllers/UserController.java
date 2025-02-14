package com.binary.carDealerApp.classCarDealerApp.controllers;

import com.binary.carDealerApp.classCarDealerApp.dto.AuthorizationRequest;
import com.binary.carDealerApp.classCarDealerApp.dto.PostNewUser;
import com.binary.carDealerApp.classCarDealerApp.dto.UserDto;
import com.binary.carDealerApp.classCarDealerApp.services.UserCredentialService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST Controller for handling User-related HTTP requests.
 * This controller provides endpoints for user management operations.
 *
 * Key points:
 * 1. Uses Spring's @RestController for automatic response body conversion.
 * 2. Maps to "/api/users" base URL for all user-related operations.
 * 3. Utilizes ResponseEntity for flexible HTTP response control.
 * 4. Implements user creation operation.
 * 5. Uses DTOs (PostNewUser and UserDto) for input and output data transfer.
 */
@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserCredentialService userCredentialService;

    /**
     * Constructor for UserController.
     * Uses constructor injection to inject UserCredentialService dependency.
     *
     * @param userCredentialService The service responsible for user credential operations.
     */
    public UserController(UserCredentialService userCredentialService) {
        this.userCredentialService = userCredentialService;
    }

    /**
     * Creates a new user in the system.
     *
     * @param postNewUser The PostNewUser object containing the new user's information.
     * @return ResponseEntity containing the created user's DTO and HTTP status CREATED.
     */
    @PostMapping("/")
    public ResponseEntity<UserDto> postNewUser(@RequestBody PostNewUser postNewUser) {
        UserDto createdUser = userCredentialService.createUser(postNewUser);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthorizationRequest request) {
        return new ResponseEntity<>(userCredentialService.login(request), HttpStatus.OK);
    }
}
