package com.binary.carDealerApp.classCarDealerApp.config;

import com.binary.carDealerApp.classCarDealerApp.exception.UserNotFoundException;
import com.binary.carDealerApp.classCarDealerApp.repositories.UserCredentialRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Configuration class for application-wide beans and security settings.
 * This class sets up the necessary components for authentication and user management.
 *
 * Key points:
 * 1. Uses @Configuration to indicate this is a configuration class.
 * 2. Configures UserDetailsService, AuthenticationProvider, AuthenticationManager, and PasswordEncoder.
 * 3. Integrates with UserCredentialRepository for user lookup.
 * 4. Implements custom user lookup logic with exception handling.
 */
@Configuration
public class ApplicationConfig {

    private UserCredentialRepository userCredentialRepository;

    /**
     * Constructor for ApplicationConfig.
     * Uses constructor injection to inject UserCredentialRepository dependency.
     *
     * @param userCredentialRepository Repository for user credential operations.
     */
    public ApplicationConfig(UserCredentialRepository userCredentialRepository) {
        this.userCredentialRepository = userCredentialRepository;
    }

    /**
     * Configures and provides a UserDetailsService bean.
     * This service is responsible for retrieving user details by username.
     *
     * @return A UserDetailsService implementation.
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> userCredentialRepository.findByUsername(username).orElseThrow(() ->
                {
                    // Throws a custom exception if the user is not found
                    throw new UserNotFoundException("User with username " + username + " not found");
                });
    }

    /**
     * Configures and provides an AuthenticationProvider bean.
     * This provider is responsible for authenticating users.
     *
     * @return A configured DaoAuthenticationProvider.
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    /**
     * Configures and provides an AuthenticationManager bean.
     * This manager is responsible for processing Authentication requests.
     *
     * @param authenticationConfiguration The AuthenticationConfiguration to use.
     * @return An AuthenticationManager instance.
     * @throws Exception If there's an error creating the AuthenticationManager.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /**
     * Configures and provides a PasswordEncoder bean.
     * This encoder is used for securely hashing passwords.
     *
     * @return A BCryptPasswordEncoder instance.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
