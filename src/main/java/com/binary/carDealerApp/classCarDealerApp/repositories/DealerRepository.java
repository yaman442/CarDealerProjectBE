package com.binary.carDealerApp.classCarDealerApp.repositories;

import com.binary.carDealerApp.classCarDealerApp.entities.Dealer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * DealerRepository Interface
 *
 * This interface defines the data access operations for the Dealer entity.
 * It extends JpaRepository, providing a comprehensive set of CRUD operations.
 *
 * Key Points:
 * 1. JpaRepository Extension: By extending JpaRepository<Dealer, Long>, this interface
 *    inherits a wide range of methods for database operations, including:
 *    - save()
 *    - findById()
 *    - findAll()
 *    - deleteById()
 *    and many more, without requiring explicit implementation.
 *
 * 2. Type Parameters:
 *    - Dealer: The entity type this repository manages.
 *    - Long: The type of the id of the Dealer entity.
 *
 * 3. @Repository Annotation: Marks this interface as a Spring Data repository.
 *    While optional when extending JpaRepository (as Spring Data JPA registers 
 *    it as a repository by default), it's included here for clarity.
 *
 * 4. Automatic Implementation: Spring Data JPA automatically generates an
 *    implementation of this interface at runtime, eliminating the need for 
 *    manual implementation of basic CRUD operations.
 *
 * 5. Extensibility: Although not present in this basic interface, custom query 
 *    methods can be added here as needed. Spring Data JPA will automatically 
 *    implement these based on method names or custom JPQL queries.
 *
 * Usage:
 * This repository can be autowired into service classes to perform
 * database operations on Dealer entities. It simplifies data access by
 * providing ready-to-use methods for common database operations.
 */
@Repository
public interface DealerRepository extends JpaRepository<Dealer, Long> {
    // No additional methods are defined here.
    // All basic CRUD operations are inherited from JpaRepository.

    // Custom query methods can be added here if needed, for example:
    // List<Dealer> findByDealerName(String name);
    // Dealer findByEmail(String email);
}