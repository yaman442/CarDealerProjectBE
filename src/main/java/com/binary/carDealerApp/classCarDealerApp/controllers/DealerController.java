package com.binary.carDealerApp.classCarDealerApp.controllers;

import com.binary.carDealerApp.classCarDealerApp.dto.DealerDto;
import com.binary.carDealerApp.classCarDealerApp.entities.Dealer;
import com.binary.carDealerApp.classCarDealerApp.services.DealerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for handling Dealer-related HTTP requests.
 * This controller provides endpoints for CRUD operations on Dealer entities.
 *
 * Key points:
 * 1. Uses Spring's @RestController for automatic response body conversion.
 * 2. Maps to "/api/dealers" base URL for all dealer-related operations.
 * 3. Utilizes ResponseEntity for flexible HTTP response control.
 * 4. Implements standard CRUD operations (Create, Read, Update, Delete).
 * 5. Uses DealerDto for input data transfer, separating API contract from entity structure.
 */
@RestController
@RequestMapping("/api/dealers")
public class DealerController {

    @Autowired
    private DealerService dealerService;

    /**
     * Retrieves all dealers from the database.
     *
     * @return ResponseEntity containing a list of all dealers and HTTP status OK.
     */
    @GetMapping("/all")
    public ResponseEntity<List<Dealer>> getAllDealers() {
        List<Dealer> dealers = dealerService.getAllDealers();
        return new ResponseEntity<>(dealers, HttpStatus.OK);
    }

    /**
     * Retrieves a specific dealer by its ID.
     *
     * @param id The ID of the dealer to retrieve.
     * @return ResponseEntity containing the requested dealer and HTTP status OK.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Dealer> getDealerById(@PathVariable Long id) {
        Dealer dealer = dealerService.getDealerById(id);
        return new ResponseEntity<>(dealer, HttpStatus.OK);
    }

    /**
     * Creates a new dealer in the database.
     *
     * @param dealerDto The DealerDto object containing the dealer information to be created.
     * @return ResponseEntity containing the created dealer and HTTP status CREATED.
     */
    @PostMapping("/create")
    public ResponseEntity<Dealer> createDealer(@RequestBody DealerDto dealerDto) {
        Dealer dealer = new Dealer();
        dealer.setDealerName(dealerDto.getName());
        return new ResponseEntity<>(dealerService.createDealer(dealer), HttpStatus.CREATED);
    }

    /**
     * Updates an existing dealer in the database.
     *
     * @param dealerDto The DealerDto object containing the updated dealer information.
     * @param id The ID of the dealer to be updated.
     * @return ResponseEntity containing the updated dealer and HTTP status OK.
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<Dealer> updateDealer(@RequestBody DealerDto dealerDto, @PathVariable Long id) {
        return new ResponseEntity<>(dealerService.updateDealer(id, dealerDto), HttpStatus.OK);
    }

    /**
     * Deletes a dealer from the database by its ID.
     *
     * @param id The ID of the dealer to be deleted.
     * @return ResponseEntity with a success message and HTTP status OK if deletion is successful.
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteDealer(@PathVariable Long id) {
        dealerService.deleteDealer(id);
        return new ResponseEntity<>("Dealer deleted with ID " + id, HttpStatus.OK);
    }
}
