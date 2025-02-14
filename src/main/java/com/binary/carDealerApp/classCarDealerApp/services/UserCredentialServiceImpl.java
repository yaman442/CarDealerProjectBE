package com.binary.carDealerApp.classCarDealerApp.services;

import com.binary.carDealerApp.classCarDealerApp.config.JwtService;
import com.binary.carDealerApp.classCarDealerApp.dto.AuthorizationRequest;
import com.binary.carDealerApp.classCarDealerApp.dto.PostNewUser;
import com.binary.carDealerApp.classCarDealerApp.dto.UserDto;
import com.binary.carDealerApp.classCarDealerApp.entities.UserCredential;
import com.binary.carDealerApp.classCarDealerApp.exception.InvalidRoleException;
import com.binary.carDealerApp.classCarDealerApp.exception.UsernameTakenException;
import com.binary.carDealerApp.classCarDealerApp.repositories.UserCredentialRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Implementation of the UserCredentialService interface.
 * This service handles user credential operations such as user creation.
 *
 * Key points:
 * 1. Uses Spring's @Service annotation for automatic discovery and injection.
 * 2. Implements password encoding for security.
 * 3. Performs username uniqueness check before user creation.
 * 4. Enforces role restrictions (prevents direct ADMIN creation).
 * 5. Uses DTOs for input and output to separate internal and external representations.
 */
@Service
public class UserCredentialServiceImpl implements UserCredentialService {

    private UserCredentialRepository userCredentialRepository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private JwtService jwtService;

    /**
     * Constructor for dependency injection.
     *
     * @param userCredentialRepository Repository for user credential operations.
     * @param passwordEncoder Encoder for securely hashing passwords.
     */
    public UserCredentialServiceImpl(
            UserCredentialRepository userCredentialRepository,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager,
            JwtService jwtService
    ) {
        this.userCredentialRepository = userCredentialRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    /**
     * Creates a new user account.
     *
     * @param postNewUser DTO containing new user information.
     * @return UserDto containing created user information.
     * @throws UsernameTakenException if the username is already in use.
     * @throws InvalidRoleException if an attempt is made to create an ADMIN user.
     */
    @Override
    public UserDto createUser(PostNewUser postNewUser) {
        // Check if username is already taken
        Optional<UserCredential> possibleNameTaken =
                userCredentialRepository
                        .findByUsername(postNewUser
                                .username()
                                .toLowerCase());
        if(possibleNameTaken.isPresent()){
            throw new UsernameTakenException("Username has been taken, Please choose another!");
        }

        // Prevent creation of ADMIN users
        if(postNewUser.role().equals("ADMIN")) {
            throw new InvalidRoleException("User cannot be created as ADMIN, has to be USER or DEALER");
        }

        // Create and save new user
        UserCredential userCredential = new UserCredential(
                postNewUser.username().toLowerCase(),
                passwordEncoder.encode(postNewUser.password()),
                postNewUser.role().toUpperCase()
        );
        userCredential = userCredentialRepository.save(userCredential);

        // Convert to DTO for return
        UserDto userDto = new UserDto(userCredential.getUsername(), userCredential.getRole());

        return userDto;
    }

    @Override
    public String login(AuthorizationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.username(),
                request.password()
        ));
        String jwt = jwtService.generateToken(request.username());
        return jwt;
    }
}