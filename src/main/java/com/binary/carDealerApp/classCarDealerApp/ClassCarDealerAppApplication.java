// Package declaration - defines the namespace for this class
package com.binary.carDealerApp.classCarDealerApp;

// Importing necessary classes and interfaces
// These imports are from our own application packages
import com.binary.carDealerApp.classCarDealerApp.entities.Car;
import com.binary.carDealerApp.classCarDealerApp.entities.Dealer;
import com.binary.carDealerApp.classCarDealerApp.repositories.CarRepository;
import com.binary.carDealerApp.classCarDealerApp.repositories.DealerRepository;

// These imports are from Spring Framework
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// @SpringBootApplication is a convenience annotation that adds all of the following:
// @Configuration: Tags the class as a source of bean definitions for the application context.
// @EnableAutoConfiguration: Tells Spring Boot to start adding beans based on classpath settings, other beans, and various property settings.
// @ComponentScan: Tells Spring to look for other components, configurations, and services in the current package.
@SpringBootApplication
public class ClassCarDealerAppApplication implements CommandLineRunner {

    // The main method is the entry point of the application
    // It uses SpringApplication.run() to launch the Spring application
    public static void main(String[] args) {
        SpringApplication.run(ClassCarDealerAppApplication.class, args);
    }

    // @Autowired annotation is used for automatic dependency injection
    // Spring will automatically create and inject CarRepository instance here
    @Autowired
    private CarRepository carRepository;

    // Similarly, Spring will create and inject DealerRepository instance here
    @Autowired
    private DealerRepository dealerRepository;

    // This method is from the CommandLineRunner interface
    // It will be executed after the application context is loaded
    @Override
    public void run(String... args) throws Exception {

        // Create a new Dealer instance for Toyota
        Dealer dealer = new Dealer();
        dealer.setDealerName("Toyota Dealership");

        // Create another Dealer instance for Volvo
        Dealer dealer2 = new Dealer();
        dealer2.setDealerName("Volvo Dealership");

        // Print dealer objects before saving
        // At this point, the dealers don't have IDs as they're not yet persisted
        System.out.println("Before saving: " + dealer);
        System.out.println("Before saving: " + dealer2);

        // Save dealer objects to the database
        // This will persist the dealers and generate IDs for them
        dealerRepository.save(dealer);
        dealerRepository.save(dealer2);

        // Print dealer objects after saving
        // Now the dealers should have IDs assigned by the database
        System.out.println("After saving: " + dealer);
        System.out.println("After saving: " + dealer2);

        // Create a new Car instance associated with the Toyota dealer
        // Note the use of the constructor to set all properties at once
        Car car = new Car("Toyota", "Camry", "red", "123ABC", 
                          "https://www.capovalleytoyota.com/new-vehicles/camry/", 
                          2025, 35000, dealer);
        // Save the car to the database
        carRepository.save(car);

        // Create another Car instance associated with the Volvo dealer
        Car car2 = new Car("Volvo", "XC60", "blue", "098ZYX", 
                           "https://www.cardekho.com/Volvo/Volvo_XC60/pictures", 
                           2017, 50000, dealer2);
        // Save the second car to the database
        carRepository.save(car2);

        // Note: In a production application, you would typically:
        // 1. Use a service layer to encapsulate business logic
        // 2. Handle exceptions properly
        // 3. Use logging instead of System.out.println
        // 4. Possibly use transactions to ensure data consistency
        // 5. Not perform database operations directly in the main application class
    }
}
