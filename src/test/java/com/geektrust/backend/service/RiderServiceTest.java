package com.geektrust.backend.service;

import com.geektrust.backend.dto.Coordinates;
import com.geektrust.backend.dto.Match;
import com.geektrust.backend.entities.Driver;
import com.geektrust.backend.entities.Rider;
import com.geektrust.backend.enums.DriverStatus;
import com.geektrust.backend.repository.impl.DriverRepositoryImpl;
import com.geektrust.backend.repository.impl.RiderRepositoryImpl;
import com.geektrust.backend.service.impl.RiderServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RiderServiceTest {

    @InjectMocks
    RiderServiceImpl riderService;

    @Mock
    RiderRepositoryImpl riderRepository;

    @Mock
    DriverRepositoryImpl driverRepository;

    @Test
    @DisplayName("Sorting drivers as per the distance. If equidistant sorting it by lexicographical order")
    public void sortingDriverBasedOnDistance_onEqual_sortByLexicographicalOrder() {

        final List<Driver> driverList = new ArrayList<>(){
            {
                add(new Driver("D1", new Coordinates(0.0D, 1.0D), DriverStatus.AVAILABLE));
                add(new Driver("D2", new Coordinates(2.0D, 3.0D), DriverStatus.AVAILABLE));
                add(new Driver("D3", new Coordinates(4.0D, 2.0D), DriverStatus.AVAILABLE));
            }
        };

        Rider rider1 = new Rider("R1", new Coordinates(3.0D, 5.0D));
        Rider rider2 = new Rider("R2", new Coordinates(1.0D, 1.0D));

        when(driverRepository.findAll()).thenReturn(driverList);
        when(riderRepository.findById(eq("R1"))).thenReturn(Optional.of(rider1));
        when(riderRepository.findById(eq("R2"))).thenReturn(Optional.of(rider2));


        Match r1 = riderService.match("R1");
        Match r2 = riderService.match("R2");

        System.out.println(r1.getDriverIds());
        System.out.println(r2.getDriverIds());

        assertEquals(Arrays.asList("D2", "D3", "D1"), r1.getDriverIds());
        assertEquals(Arrays.asList("D1", "D2", "D3"), r2.getDriverIds());
    }

    @Test
    @DisplayName("Sorting drivers in lexicographical order case")
    public void sortingDriverBasedOnDistance_EqualDistance_sortByLexicographicalOrder() {

        final List<Driver> driverList = new ArrayList<>(){
            {
                add(new Driver("D3", new Coordinates(0.0D, 1.0D), DriverStatus.AVAILABLE));
                add(new Driver("D2", new Coordinates(2.0D, 2.0D), DriverStatus.AVAILABLE));
                add(new Driver("D1", new Coordinates(1.0D, 0.0D), DriverStatus.AVAILABLE));
                add(new Driver("D4", new Coordinates(2.0D, 2.0D), DriverStatus.AVAILABLE));
            }
        };

        Rider rider1 = new Rider("R1", new Coordinates(0.0D, 0.0D));

        when(driverRepository.findAll()).thenReturn(driverList);
        when(riderRepository.findById(eq("R1"))).thenReturn(Optional.of(rider1));


        Match r1 = riderService.match("R1");

        System.out.println(r1.getDriverIds());

        assertEquals(Arrays.asList("D1", "D3", "D2", "D4"), r1.getDriverIds());
    }

    @Test
    @DisplayName("Avoiding if the distance is greater than 5kms")
    public void avoidingDriver_ifDistanceIsGreaterThan_5Kms() {

        final List<Driver> driverList = new ArrayList<>(){
            {
                add(new Driver("D3", new Coordinates(0.0D, 1.0D), DriverStatus.AVAILABLE));
                add(new Driver("D2", new Coordinates(2.0D, 2.0D), DriverStatus.AVAILABLE));
                add(new Driver("D1", new Coordinates(12.0D, 0.0D), DriverStatus.AVAILABLE));
                add(new Driver("D4", new Coordinates(2.0D, 2.0D), DriverStatus.AVAILABLE));
            }
        };

        Rider rider1 = new Rider("R1", new Coordinates(0.0D, 0.0D));

        when(driverRepository.findAll()).thenReturn(driverList);
        when(riderRepository.findById(eq("R1"))).thenReturn(Optional.of(rider1));


        Match r1 = riderService.match("R1");

        System.out.println(r1.getDriverIds());

        assertEquals(Arrays.asList("D3", "D2", "D4"), r1.getDriverIds());
    }

}
