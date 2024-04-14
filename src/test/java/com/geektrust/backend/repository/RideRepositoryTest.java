package com.geektrust.backend.repository;

import com.geektrust.backend.entities.Ride;
import com.geektrust.backend.enums.RideStatus;
import com.geektrust.backend.repository.impl.RideRepositoryImpl;
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

public class RideRepositoryTest {

    private IRideRepository rideRepository;

    @BeforeEach
    public void setUp() {

        Map<String, Ride> rideMap = new HashMap<>() {
            {
                put("RIDE-001", new Ride("RIDE-001", "R1", "D1", RideStatus.STARTED));
                put("RIDE-002", new Ride("RIDE-002", "R2", "D2", RideStatus.STARTED));
                put("RIDE-003", new Ride("RIDE-003", "R3", "D3", RideStatus.STOPPED));
            }
        };

        rideRepository = new RideRepositoryImpl(rideMap);
    }

    @Test
    @DisplayName("save method should create and return new ride")
    public void saveRide() {
        Ride ride = new Ride("RIDE-008", "R10", "D10", RideStatus.STARTED);

        Ride actualResult = rideRepository.save(ride);

        assertNotNull(actualResult);
        assertEquals(ride, actualResult);
    }


    @Test
    @DisplayName("findAll method should return All Rides")
    public void findAllRides(){
        //Arrange
        int expectedCount = 3;
        //Act
        List<Ride> actualRides = rideRepository.findAll();
        //Assert
        Assertions.assertEquals(expectedCount,actualRides.size());
    }

    @Test
    @DisplayName("findById method should return Ride Given Id")
    public void findById_ShouldReturnRide_GivenRideId(){
        //Arrange
        String expectedRideId = "RIDE-001";
        //Act
        Optional<Ride> actualRide = rideRepository.findById(expectedRideId);
        //Assert
        String actualResult =  actualRide.map(Ride::getId).orElse(null);
        Assertions.assertEquals(expectedRideId, actualResult);
    }


    @Test
    @DisplayName("findById method | should return empty Optional if Ride is Not Found")
    public void findById_ShouldReturnEmptyIfRideNotFound(){
        //Arrange
        Optional<Ride> expected = Optional.empty();
        //Act
        Optional<Ride> actual = rideRepository.findById("RIDE-102");
        //Assert
        Assertions.assertEquals(expected,actual);
    }

    @Test
    @DisplayName("Ride if exists in the db")
    public void ride_ifExistsById() {

        // act
        boolean expectedResult = rideRepository.existsById("RIDE-001");

        // assert
        assertTrue(expectedResult);
    }

    @Test
    @DisplayName("Ride if exists in the db")
    public void ride_ifNotExistsById() {

        // Act
        boolean expectedResult = rideRepository.existsById("RIDE-200");

        // assert
        assertFalse(expectedResult);
    }
}
