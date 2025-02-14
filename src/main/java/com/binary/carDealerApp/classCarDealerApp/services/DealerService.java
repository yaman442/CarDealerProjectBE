package com.binary.carDealerApp.classCarDealerApp.services;

import com.binary.carDealerApp.classCarDealerApp.dto.DealerDto;
import com.binary.carDealerApp.classCarDealerApp.entities.Dealer;
import com.binary.carDealerApp.classCarDealerApp.repositories.DealerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * DealerService Interface
 *
 * This interface defines the contract for dealer-related operations in the application.
 * It outlines the methods that any implementing class must provide to handle
 * dealer data management.
 *
 * Key Points:
 * 1. Interface Definition: This interface establishes a contract for dealer operations,
 *    promoting loose coupling and allowing for different implementations.
 *
 * 2. CRUD Operations: The interface includes methods for Create, Read, Update, and Delete
 *    operations on Dealer entities, following standard data management practices.
 *
 * 3. Use of DTOs: The updateDealer method uses a DealerDto, demonstrating the use of
 *    Data Transfer Objects for data manipulation, which can help in separating
 *    presentation logic from domain logic.
 *
 * 4. Separation of Concerns: By defining these methods in an interface, we separate
 *    the specification of what operations are available from their implementation,
 *    adhering to the Interface Segregation Principle.
 *
 * 5. Spring Framework Integration: While not explicitly shown in the interface,
 *    implementations of this interface would typically be annotated with @Service
 *    in a Spring application, making them part of the service layer.
 *
 * 6. Exception Handling: The interface methods don't specify exceptions, but implementations
 *    might throw exceptions for scenarios like dealer not found or validation errors.
 *
 * Note: The actual implementation of these methods would be provided in a class
 * that implements this interface, typically named DealerServiceImpl.
 */
public interface DealerService {

    /**
     * Retrieves all dealers from the database.
     *
     * @return List<Dealer> A list containing all Dealer objects
     */
    List<Dealer> getAllDealers();

    /**
     * Retrieves a specific dealer by their ID.
     *
     * @param id The ID of the dealer to retrieve
     * @return Dealer The Dealer object if found
     * @throws SomeException if the dealer is not found (to be defined in implementation)
     */
    Dealer getDealerById(Long id);

    /**
     * Creates a new dealer entry in the database.
     *
     * @param dealer The Dealer object to be created
     * @return Dealer The created Dealer object with its generated ID
     */
    Dealer createDealer(Dealer dealer);

    /**
     * Updates an existing dealer's information.
     *
     * @param id The ID of the dealer to be updated
     * @param dealerDto The DealerDto object containing the updated information
     * @return Dealer The updated Dealer object
     * @throws SomeException if the dealer is not found (to be defined in implementation)
     */
    Dealer updateDealer(Long id, DealerDto dealerDto);

    /**
     * Deletes a dealer from the database based on its ID.
     *
     * @param id The ID of the dealer to be deleted
     * @throws SomeException if the dealer is not found (to be defined in implementation)
     */
    void deleteDealer(Long id);
}

