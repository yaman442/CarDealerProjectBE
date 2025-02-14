package com.binary.carDealerApp.classCarDealerApp.services;

import com.binary.carDealerApp.classCarDealerApp.dto.DealerDto;
import com.binary.carDealerApp.classCarDealerApp.entities.Dealer;
import com.binary.carDealerApp.classCarDealerApp.repositories.DealerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * DealerServiceImpl - Implementation of the DealerService interface
 *
 * This class provides the concrete implementation of dealer-related operations
 * defined in the DealerService interface. It acts as an intermediary between
 * the controller layer and the data access layer (repository).
 *
 * Key Points:
 * 1. @Service Annotation: Marks this class as a service component in Spring,
 *    allowing automatic detection and instantiation.
 *
 * 2. Implementation of Interface: This class implements the DealerService interface,
 *    providing concrete implementations for all defined methods.
 *
 * 3. Dependency Injection: Uses @Autowired to inject DealerRepository,
 *    demonstrating Spring's dependency injection capability.
 *
 * 4. CRUD Operations: Implements Create, Read, Update, and Delete operations
 *    for Dealer entities using the injected repository.
 *
 * 5. Optional Handling: Uses Java's Optional class to handle potential null values
 *    when retrieving dealers by ID.
 *
 * 6. DTO Usage: Employs Data Transfer Objects (DTOs) in the update operation,
 *    showing a separation between API and domain models.
 *
 * 7. Null Checks: Includes null checks in update operations to ensure data integrity.
 *
 * 8. Error Handling: Currently returns null for not-found scenarios. Consider
 *    throwing specific exceptions for better error handling.
 */
@Service
public class DealerServiceImpl implements DealerService {

    @Autowired
    private DealerRepository dealerRepository;

    /**
     * Retrieves all dealers from the database.
     *
     * @return List<Dealer> A list containing all Dealer objects
     */
    @Override
    public List<Dealer> getAllDealers() {
        return dealerRepository.findAll();
    }

    /**
     * Retrieves a specific dealer by their ID.
     *
     * @param id The ID of the dealer to retrieve
     * @return Dealer The Dealer object if found, null otherwise
     * Note: Consider throwing a custom exception instead of returning null
     */
    @Override
    public Dealer getDealerById(Long id) {
        Optional<Dealer> optionalDealer = dealerRepository.findById(id);

        if (optionalDealer.isPresent()) {
            return optionalDealer.get();
        }
        return null; // Consider throwing a custom exception here
    }

    /**
     * Creates a new dealer entry in the database.
     *
     * @param dealer The Dealer object to be created
     * @return Dealer The created Dealer object with its generated ID
     */
    @Override
    public Dealer createDealer(Dealer dealer) {
        return dealerRepository.save(dealer);
    }

    /**
     * Updates an existing dealer's information.
     *
     * @param id The ID of the dealer to be updated
     * @param dealerDto The DealerDto object containing the updated information
     * @return Dealer The updated Dealer object, or null if not found or no changes
     * Note: Consider throwing exceptions for not-found or no-change scenarios
     */
    @Override
    public Dealer updateDealer(Long id, DealerDto dealerDto) {
        Optional<Dealer> optionalDealer = dealerRepository.findById(id);
        if (optionalDealer.isPresent()) {
            Dealer tobeUpdatedDealer = optionalDealer.get();
            if (dealerDto.getName() != null) {
                tobeUpdatedDealer.setDealerName(dealerDto.getName());
                return dealerRepository.save(tobeUpdatedDealer);
            }
        }
        return null; // Consider throwing an exception for not found or no changes
    }

    /**
     * Deletes a dealer from the database based on its ID.
     *
     * @param id The ID of the dealer to be deleted
     * Note: This method doesn't check if the dealer exists before deletion.
     * Consider adding a check and throwing an exception if the dealer is not found.
     */
    @Override
    public void deleteDealer(Long id) {
        dealerRepository.deleteById(id);
    }
}