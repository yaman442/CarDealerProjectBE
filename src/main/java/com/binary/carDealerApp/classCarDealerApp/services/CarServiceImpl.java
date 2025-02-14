package com.binary.carDealerApp.classCarDealerApp.services;

import com.binary.carDealerApp.classCarDealerApp.entities.Car;
import com.binary.carDealerApp.classCarDealerApp.exception.CarNotFoundException;
import com.binary.carDealerApp.classCarDealerApp.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * CarServiceImpl - Implementation of the CarService interface
 *
 * This class provides the concrete implementation of the car-related operations
 * defined in the CarService interface. It acts as a bridge between the controller
 * layer and the data access layer (repository).
 *
 * @Service: This annotation marks this class as a service component in Spring.
 *           It allows Spring to detect and automatically create an instance of this class
 *           (bean) to be used in other parts of the application.
 *
 * Key Points:
 * 1. @Service Annotation: This is a Spring stereotype annotation. It indicates that
 *    this class is a service component in the application. Spring will automatically
 *    detect and create an instance of this class.
 *
 * 2. Implementation of Interface: This class implements the CarService interface,
 *    providing concrete implementations for all the methods defined in the interface.
 *
 * 3. Dependency Injection: The @Autowired annotation on CarRepository field demonstrates
 *    dependency injection. Spring automatically provides an instance of CarRepository to this service.
 *
 * 4. Exception Handling: The class uses custom exceptions (e.g., CarNotFoundException)
 *    to handle error scenarios. This is a good practice for providing meaningful error messages.
 *
 * 5. Business Logic: The createCar method includes business logic to validate the car's year.
 *    This demonstrates how service classes can contain business rules and validations.
 *
 * 6. Repository Usage: The class uses CarRepository to interact with the database,
 *    demonstrating the separation of concerns between the service layer and data access layer.
 *
 * 7. Optional Handling: In the getCarById method, the use of orElseThrow demonstrates
 *    how to handle the Optional return type from the repository's findById method.
 *
 * 8. Method Implementations: Each method provides a concrete implementation of the
 *    operations defined in the CarService interface, showing how the service layer
 *    acts as an intermediary between controllers and repositories.
 */
@Service
public class CarServiceImpl implements CarService {

    /**
     * CarRepository instance
     *
     * @Autowired: This annotation tells Spring to automatically inject an instance
     *             of CarRepository into this field. It's a form of dependency injection.
     */
    @Autowired
    private CarRepository carRepository;

    /**
     * Retrieves all cars from the database.
     *
     * @return List<Car> A list containing all Car objects in the database
     */
    @Override
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    /**
     * Creates a new car entry in the database.
     *
     * This method includes a validation check to ensure the car's year is not before 1908.
     *
     * @param car The Car object to be created
     * @return Car The created Car object with its generated ID
     * @throws IllegalArgumentException if the car's year is before 1908
     */
    @Override
    public Car createCar(Car car) {
        if(car.getYear() < 1908) {
            IllegalArgumentException exception =
                    new IllegalArgumentException("Cars weren't invented till 1908 anything before is unknown");
            System.out.println("Service throwing exception with message: " + exception.getMessage());
            throw exception;
        }
        return carRepository.save(car);
    }

    /**
     * Updates an existing car's information in the database.
     *
     * @param car The Car object with updated information
     * @param id The ID of the car to be updated
     * @return Car The updated Car object
     * @throws CarNotFoundException if no car with the given id is found
     */
    @Override
    public Car updateCar(Car car, Long id) {
        Car oldCar = getCarById(id);
        return carRepository.save(car);
    }

    /**
     * Deletes a car from the database based on its ID.
     *
     * @param id The ID of the car to be deleted
     * @throws CarNotFoundException if no car with the given id is found
     */
    @Override
    public void deleteCarById(Long id) {
        Car car = getCarById(id);
        carRepository.delete(car);
    }

    /**
     * Retrieves a specific car from the database by its ID.
     *
     * @param id The ID of the car to retrieve
     * @return Car The Car object if found
     * @throws CarNotFoundException if no car with the given id is found
     */
    @Override
    public Car getCarById(Long id) {
        return carRepository.findById(id)
                .orElseThrow(() ->
                        new CarNotFoundException("Car with id of " +
                                id +
                                " is not found"));
    }
}