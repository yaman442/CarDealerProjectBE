package com.binary.carDealerApp.classCarDealerApp.repositories;

import com.binary.carDealerApp.classCarDealerApp.entities.UserCredential;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;

/**
 * UserCredentialRepository Interface
 *
 * This interface defines the data access operations for the UserCredential entity.
 * It extends ListCrudRepository, providing a set of CRUD operations that return List
 * instead of Iterable.
 *
 * Key Points:
 * 1. ListCrudRepository Extension: By extending ListCrudRepository<UserCredential, String>,
 *    this interface inherits CRUD operations that return List, including:
 *    - save()
 *    - findById()
 *    - findAll() (returns List instead of Iterable)
 *    - deleteById()
 *    This choice suggests a preference for List return types in the application.
 *
 * 2. Type Parameters:
 *    - UserCredential: The entity type this repository manages.
 *    - String: The type of the id of the UserCredential entity, implying username is used as the primary key.
 *
 * 3. Custom Query Method:
 *    - findByUsername: This method is automatically implemented by Spring Data JPA.
 *    - It returns an Optional<UserCredential>, allowing for null-safe operations.
 *    - This method is crucial for authentication processes, enabling username-based lookups.
 *
 * 4. Optional Return Type: The use of Optional for the custom query method aligns with
 *    Java 8+ best practices for handling potentially null results.
 *
 * 5. No @Repository Annotation: Unlike some other repository interfaces, this one doesn't
 *    use the @Repository annotation. It's not necessary here as Spring Data JPA
 *    automatically detects and implements repository interfaces.
 *
 * Usage:
 * This repository is typically autowired into service classes handling user authentication
 * and management. The findByUsername method is particularly useful for user lookup during
 * login processes or user-specific operations.
 */
public interface UserCredentialRepository extends ListCrudRepository<UserCredential, String> {
    /**
     * Finds a UserCredential by username.
     *
     * @param username The username to search for
     * @return An Optional containing the UserCredential if found, or an empty Optional if not found
     */
    Optional<UserCredential> findByUsername(String username);
}