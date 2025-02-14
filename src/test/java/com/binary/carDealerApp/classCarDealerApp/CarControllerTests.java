package com.binary.carDealerApp.classCarDealerApp;

import com.binary.carDealerApp.classCarDealerApp.entities.Car;
import com.binary.carDealerApp.classCarDealerApp.services.CarService;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.UUID;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CarControllerTests {

    @LocalServerPort
    private int port;

    @MockitoBean
    private CarService carService;

    private Car testCar;

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;

        testCar = new Car();
        testCar.setId(1L);
        testCar.setBrand("Tesla");
        testCar.setModel("Model 3");
        testCar.setYear(2020);
        testCar.setRegNumber(UUID.randomUUID().toString());
        testCar.setImageUrl("tesla.com");
        testCar.setColor("Purple");
        testCar.setPrice(35000);
    }

    @Test
    void createCar_WithValidDate_ShouldReturnCreatedCar() {
        Mockito.when(carService.createCar(any(Car.class)))
                .thenReturn(testCar);

        String json = """
                {
                    "brand": "Tesla",
                    "model": "Model 3",
                    "year": 2020,
                    "regNumber": "12rgt34g54g34g34-23f243-t234-f322-234",
                    "imageUrl": "tesla.com",
                    "color": "Purple",
                    "price": 35000
                }
                """;

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(json)
                .when()
                .post("/api/cars/")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("brand", equalTo("Tesla"))
                .body("model", equalTo("Model 3"))
                .body("year", equalTo(2020));
    }

    @Test
    void createCar_WithInvalidYear_ShouldReturnApiError() {
        // Store the message in a constant for consistency
        final String ERROR_MESSAGE = "Cars weren't invented till 1908 anything before is unknown";

        // Create the exception explicitly
        IllegalArgumentException expectedException = new IllegalArgumentException(ERROR_MESSAGE);

        // Use the explicit exception in the mock
        Mockito.when(carService.createCar(Mockito.any(Car.class)))
                .thenThrow(expectedException);

        String json = """
            {
                "brand": "Tesla",
                "model": "Model 3",
                "year": 1907,
                "regNumber": "12rgt34g54g34g34-23f243-t234-f322-234",
                "imageUrl": "tesla.com",
                "color": "Purple",
                "price": 35000
            }
            """;

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(json)
                .when()
                .post("/api/cars/")
                .then()
                .log().all()  // This helps us see the actual response
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("message", equalTo(ERROR_MESSAGE))
                .body("path", equalTo("/api/cars/"))
                .body("statusCode", equalTo(400));
    }
}
