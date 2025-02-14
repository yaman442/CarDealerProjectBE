package com.binary.carDealerApp.classCarDealerApp.repositories;

import com.binary.carDealerApp.classCarDealerApp.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * CarRepository Interface
 *
 * This interface defines the data access operations for the Car entity.
 * It extends JpaRepository, which provides a full set of CRUD operations.
 *
 * Key Points:
 * 1. JpaRepository Extension: By extending JpaRepository<Car, Long>, this interface
 *    inherits a wide range of methods for database operations, including:
 *    - save()
 *    - findById()
 *    - findAll()
 *    - deleteById()
 *    and many more, without needing to implement them explicitly.
 *
 * 2. Type Parameters:
 *    - Car: The entity type this repository manages.
 *    - Long: The type of the id of the Car entity.
 *
 * 3. @Repository Annotation: Marks this interface as a Spring Data repository.
 *    This annotation is optional when extending JpaRepository, as Spring Data
 *    JPA registers it as a repository by default.
 *
 * 4. Custom Query Methods: While not present in this interface, you can define
 *    custom query methods here if needed, and Spring Data JPA will implement
 *    them automatically based on the method name or custom JPQL queries.
 *
 * 5. Automatic Implementation: Spring Data JPA automatically creates an
 *    implementation of this interface at runtime.
 *
 * Usage:
 * This repository can be autowired into service classes to perform
 * database operations on Car entities without writing explicit queries
 * for basic CRUD operations.
 */
@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    // No additional methods are defined here.
    // All basic CRUD operations are inherited from JpaRepository.

    // Custom query methods can be added here if needed, for example:
    // List<Car> findByBrand(String brand);
    // List<Car> findByYearGreaterThan(int year);
}