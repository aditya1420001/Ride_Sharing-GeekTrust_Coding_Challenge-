package com.geektrust.backend.repository;

import com.geektrust.backend.dto.Coordinates;
import com.geektrust.backend.entities.Driver;
import com.geektrust.backend.enums.DriverStatus;
import com.geektrust.backend.repository.impl.DriverRepositoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class DriverRepositoryTest {

    private DriverRepositoryImpl driverRepository;

    @BeforeEach
    public void setUp() {

        final Map<String, Driver> driverMap = new HashMap<>() {
            {
                put("D1", new Driver("D1", new Coordinates(1.0D, 2.0D), DriverStatus.AVAILABLE));
                put("D2", new Driver("D2", new Coordinates(3.0D, 5.0D), DriverStatus.AVAILABLE));
                put("D3", new Driver("D3", new Coordinates(2.0D, 3.0D), DriverStatus.NOT_AVAILABLE));
                put("D4", new Driver("D4", new Coordinates(1.0D, 0.0D), DriverStatus.AVAILABLE));
            }
        };

        driverRepository = new DriverRepositoryImpl(driverMap);
    }

    @Test
    @DisplayName("save method should create and return new Driver")
    public void saveDriver() {
        Driver driver = new Driver("D5", new Coordinates(5.0D, 1.0D), DriverStatus.AVAILABLE);

        Driver actualResult = driverRepository.save(driver);

        assertNotNull(actualResult);
        assertEquals(driver, actualResult);
    }


    @Test
    @DisplayName("findAll method should return All Drivers")
    public void findAllDrivers(){
        //Arrange
        int expectedCount = 4;
        //Act
        List<Driver> actualDrivers = driverRepository.findAll();
        //Assert
        Assertions.assertEquals(expectedCount,actualDrivers.size());
    }

    @Test
    @DisplayName("findById method should return Driver Given Id")
    public void findById_ShouldReturnDriver_GivenDriverId(){
        //Arrange
        String expectedDriverId = "D1";
        //Act
        Optional<Driver> actualDriver = driverRepository.findById(expectedDriverId);
        //Assert
        String actualResult = actualDriver.map(Driver::getId).orElse(null);
        Assertions.assertEquals(expectedDriverId, actualResult);
    }


    @Test
    @DisplayName("findById method | should return empty Optional if Driver is Not Found")
    public void findById_ShouldReturnEmptyIfDriverNotFound(){
        //Arrange
        Optional<Driver> expected = Optional.empty();
        //Act
        Optional<Driver> actual = driverRepository.findById("D20");
        //Assert
        Assertions.assertEquals(expected,actual);
    }


    @Test
    @DisplayName("find all the available drivers")
    public void findAllAvailableDrivers_ShouldReturnAllAvailableDrivers(){
        //Arrange
        List<Driver> expectedResult = new ArrayList<>() {
            {
                add(new Driver("D1", new Coordinates(1.0D, 2.0D), DriverStatus.AVAILABLE));
                add(new Driver("D2", new Coordinates(3.0D, 5.0D), DriverStatus.AVAILABLE));
                add(new Driver("D4", new Coordinates(1.0D, 0.0D), DriverStatus.AVAILABLE));
            }
        };
        //Act
        List<Driver> actualResult = driverRepository.findAllAvailableDrivers();
        //Assert
        assertEquals(expectedResult.size(), actualResult.size());
    }

    @Test
    @DisplayName("Driver if exists in the db")
    public void Driver_ifExistsById() {

        // act
        boolean expectedResult = driverRepository.existsById("D1");

        // assert
        assertTrue(expectedResult);
    }

    @Test
    @DisplayName("Driver if exists in the db")
    public void Driver_ifNotExistsById() {

        // Act
        boolean expectedResult = driverRepository.existsById("D20");

        // assert
        assertFalse(expectedResult);
    }

}
