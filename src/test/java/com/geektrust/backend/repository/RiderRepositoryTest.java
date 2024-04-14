package com.geektrust.backend.repository;

import com.geektrust.backend.dto.Coordinates;
import com.geektrust.backend.entities.Rider;
import com.geektrust.backend.repository.impl.RiderRepositoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("RiderRepositoryTest")
public class RiderRepositoryTest {
    private IRiderRepository riderRepository;

    @BeforeEach
    public void setUp() {

        Map<String, Rider> ridermap = new HashMap<>() {
            {
                put("R1", new Rider("R1", new Coordinates(1.0D, 1.0D)));
                put("R2", new Rider("R2", new Coordinates(2.0D, 1.0D)));
                put("R3", new Rider("R3", new Coordinates(3.0D, 0.0D)));
            }
        };

        riderRepository = new RiderRepositoryImpl(ridermap);
    }

    @Test
    @DisplayName("save method should create and return new rider")
    public void saveRider() {
        Rider rider = new Rider("R5", new Coordinates(1.0D, 1.0D));

        Rider actualResult = riderRepository.save(rider);

        assertNotNull(actualResult);
        assertEquals(rider, actualResult);
    }


    @Test
    @DisplayName("findAll method should return All riders")
    public void findAllRiders(){
        //Arrange
        int expectedCount = 3;
        //Act
        List<Rider> actualRiders = riderRepository.findAll();
        //Assert
        Assertions.assertEquals(expectedCount,actualRiders.size());
    }

    @Test
    @DisplayName("findById method should return Riders Given Id")
    public void findById_ShouldReturnRiders_GivenRideId(){
        //Arrange
        String expectedRiderId = "R1";
        //Act
        Optional<Rider> actualRider = riderRepository.findById(expectedRiderId);
        //Assert
        String actualResult =  actualRider.map(Rider::getId).orElse(null);
        Assertions.assertEquals(expectedRiderId, actualResult);
    }


    @Test
    @DisplayName("findById method | should return empty Optional if Rider is Not Found")
    public void findById_ShouldReturnEmptyIfRiderNotFound(){
        //Arrange
        Optional<Rider> expected = Optional.empty();
        //Act
        Optional<Rider> actual = riderRepository.findById("R10");
        //Assert
        Assertions.assertEquals(expected,actual);
    }

    @Test
    @DisplayName("Rider if exists in the db")
    public void rider_ifExistsById() {
        // act
        boolean expectedResult = riderRepository.existsById("R1");

        // assert
        assertTrue(expectedResult);
    }

    @Test
    @DisplayName("Rider if exists in the db")
    public void rider_ifNotExistsById() {
        // Act
        boolean expectedResult = riderRepository.existsById("R200");

        // assert
        assertFalse(expectedResult);
    }
}
