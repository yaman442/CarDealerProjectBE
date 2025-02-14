package com.binary.carDealerApp.classCarDealerApp.controllers;

import com.binary.carDealerApp.classCarDealerApp.entities.Car;
import com.binary.carDealerApp.classCarDealerApp.entities.Dealer;
import com.binary.carDealerApp.classCarDealerApp.services.CarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for handling Car-related HTTP requests.
 * This controller provides endpoints for CRUD operations on Car entities.
 *
 * Key points:
 * 1. Uses Spring's @RestController for automatic response body conversion.
 * 2. Maps to "/api/cars" base URL for all car-related operations.
 * 3. Utilizes ResponseEntity for flexible HTTP response control.
 * 4. Implements standard CRUD operations (Create, Read, Update, Delete).
 * 5. Logs important information using SLF4J.
 */
@RestController
@RequestMapping("/api/cars")
public class CarController {

    private static final Logger log = LoggerFactory.getLogger(CarController.class);
    @Autowired
    private CarService carService;

    /**
     * Handles GET requests to the home endpoint.
     *
     * @return A welcome message string.
     */
    @GetMapping("/home")
    public String home() {
        return "Welcome to Car Dealer!";
    }

    /**
     * Retrieves all cars from the database.
     *
     * @return ResponseEntity containing a list of all cars and HTTP status OK.
     */
    @GetMapping("/all")
    public ResponseEntity<List<Car>> getAllCars() {
        List<Car> cars = carService.getAllCars();
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    /**
     * Creates a new car in the database.
     *
     * @param car The Car object to be created, received in the request body.
     * @return ResponseEntity containing the created car and HTTP status CREATED.
     */
    @PostMapping("/")
    public ResponseEntity<Car> createCar(@RequestBody Car car) {
        Car returnCar = carService.createCar(car);
        return new ResponseEntity<>(returnCar, HttpStatus.CREATED);
    }

    /**
     * Updates an existing car in the database.
     *
     * @param id The ID of the car to be updated.
     * @param car The updated Car object, received in the request body.
     * @return ResponseEntity containing the updated car and HTTP status OK.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable Long id, @RequestBody Car car) {
        log.info(car.toString());
        Car updatedCar = carService.updateCar(car, id);
        return new ResponseEntity<>(updatedCar, HttpStatus.OK);
    }

    /**
     * Deletes a car from the database by its ID.
     *
     * @param id The ID of the car to be deleted.
     * @return ResponseEntity with HTTP status OK if deletion is successful.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity deleteCar(@PathVariable Long id) {
        carService.deleteCarById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Retrieves a specific car by its ID.
     *
     * @param id The ID of the car to retrieve.
     * @return ResponseEntity containing the requested car and HTTP status OK.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable Long id) {
        Car car = carService.getCarById(id);
        return new ResponseEntity<>(car, HttpStatus.OK);
    }
}
