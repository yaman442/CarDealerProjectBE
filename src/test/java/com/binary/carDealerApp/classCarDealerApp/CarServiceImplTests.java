package com.binary.carDealerApp.classCarDealerApp;

import com.binary.carDealerApp.classCarDealerApp.entities.Car;
import com.binary.carDealerApp.classCarDealerApp.exception.CarNotFoundException;
import com.binary.carDealerApp.classCarDealerApp.repositories.CarRepository;
import com.binary.carDealerApp.classCarDealerApp.services.CarServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CarServiceImplTests {

    @InjectMocks
    private CarServiceImpl carService;

    @Mock
    private CarRepository carRepository;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() throws Exception {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void takedown() throws Exception {
        closeable.close();
    }

    @Test
    void CarServiceImpl_createCar_ShouldSucceed() {
        String regNum = UUID.randomUUID().toString();
        Car databaseCar = new Car();
        databaseCar.setId(1L);
        databaseCar.setBrand("Ford");
        databaseCar.setModel("Ranger");
        databaseCar.setColor("Brown");
        databaseCar.setImageUrl("randomUrl.net");
        databaseCar.setRegNumber(regNum);
        databaseCar.setYear(2014);
        databaseCar.setPrice(35000.00);
        Car newCar = new Car();
        newCar.setBrand("Ford");
        newCar.setModel("Ranger");
        newCar.setColor("Brown");
        newCar.setImageUrl("randomUrl.net");
        newCar.setRegNumber(regNum);
        newCar.setYear(2014);
        newCar.setPrice(35000.00);
        Mockito.when(carRepository.save(newCar)).thenReturn(databaseCar);

        Car result = carService.createCar(newCar);

        Mockito.verify(carRepository, Mockito.times(1)).save(newCar);
        assertThat(result).isEqualTo(databaseCar);
    }

    @Test
    void CarServiceImple_createCar_ShouldThrowIfYearBefore1908(){
        String regNum = UUID.randomUUID().toString();
        Car newCar = new Car();
        newCar.setBrand("Ford");
        newCar.setModel("Ranger");
        newCar.setColor("Brown");
        newCar.setImageUrl("randomUrl.net");
        newCar.setRegNumber(regNum);
        newCar.setYear(1907);
        newCar.setPrice(35000.00);

        Throwable throwable = assertThrows(Exception.class, () -> carService.createCar(newCar));

        assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
        assertThat(throwable.getMessage()).isEqualTo("Cars weren't invented till 1908 anything before is unknown");
    }

    @Test
    void CarServiceImpl_getCarById_ShouldSucceed() {
        String regNum = UUID.randomUUID().toString();
        Car databaseCar = new Car();
        databaseCar.setId(1L);
        databaseCar.setBrand("Ford");
        databaseCar.setModel("Ranger");
        databaseCar.setColor("Brown");
        databaseCar.setImageUrl("randomUrl.net");
        databaseCar.setRegNumber(regNum);
        databaseCar.setYear(2014);
        databaseCar.setPrice(35000.00);

        Mockito.when(carRepository.findById(1L)).thenReturn(Optional.of(databaseCar));

        Car result = carService.getCarById(1L);

        Mockito.verify(carRepository, Mockito.times(1)).findById(1L);
        assertThat(result).isEqualTo(databaseCar);
    }

    @Test
    void CarServiceImpl_getCarById_ShouldThrowInvalidId() {
        Throwable throwable = assertThrows(Exception.class, () -> carService.getCarById(150l));

        assertThat(throwable).isInstanceOf(CarNotFoundException.class);
        assertThat(throwable.getMessage()).isEqualTo("Car with id of 150 is not found");
    }

    @Test
    void CarServiceImpl_updateCar_ShouldSucceed() {
        String regNum = UUID.randomUUID().toString();
        Car databaseCar = new Car();
        databaseCar.setId(1L);
        databaseCar.setBrand("Ford");
        databaseCar.setModel("Ranger");
        databaseCar.setColor("Brown");
        databaseCar.setImageUrl("randomUrl.net");
        databaseCar.setRegNumber(regNum);
        databaseCar.setYear(2014);
        databaseCar.setPrice(35000.00);
        Car updatedCar = new Car();
        updatedCar.setId(1L);
        updatedCar.setBrand("Audi");
        updatedCar.setModel("A6");
        updatedCar.setColor("Purple");
        updatedCar.setImageUrl("audi.net");
        updatedCar.setRegNumber(regNum);
        updatedCar.setYear(2020);
        updatedCar.setPrice(75000.00);

        Mockito.when(carRepository.findById(1L)).thenReturn(Optional.of(databaseCar));
        Mockito.when(carRepository.save(updatedCar)).thenReturn(updatedCar);

        Car result = carService.updateCar(updatedCar, 1L);

        Mockito.verify(carRepository, Mockito.times(1)).save(updatedCar);
        assertThat(result).isNotEqualTo(databaseCar);
        assertThat(result).isEqualTo(updatedCar);
    }

    @Test
    void CarServiceImpl_deleteCarById_ShouldSucceed() {
        String regNum = UUID.randomUUID().toString();
        Car databaseCar = new Car();
        databaseCar.setId(1L);
        databaseCar.setBrand("Ford");
        databaseCar.setModel("Ranger");
        databaseCar.setColor("Brown");
        databaseCar.setImageUrl("randomUrl.net");
        databaseCar.setRegNumber(regNum);
        databaseCar.setYear(2014);
        databaseCar.setPrice(35000.00);

        Mockito.when(carRepository.findById(1L)).thenReturn(Optional.of(databaseCar));


        carService.deleteCarById(1L);

        Mockito.verify(carRepository, Mockito.times(1)).findById(1L);
        Mockito.verify(carRepository, Mockito.times(1)).delete(databaseCar);
    }

}
