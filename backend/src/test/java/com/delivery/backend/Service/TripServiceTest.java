package com.delivery.backend.Service;

import com.delivery.backend.DTO.TripDTO;
import com.delivery.backend.Mappers.TripMapper;
import com.delivery.backend.Model.Driver;
import com.delivery.backend.Model.Trip;
import com.delivery.backend.Repositories.TripRepository;
import com.delivery.backend.Repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class TripServiceTest {

    //fake repo with real dependencies
    @Mock
    private TripRepository repository;

    @Mock
    private TripMapper mapper;

    @Mock
    private UserRepository userRepository;

    @Mock
    private SecurityContext securityContext;

    @Mock
    private Authentication authentication;

    // This is the REAL service with fake dependencies
    @InjectMocks
    private TripService tripService;

    // This runs before each test to set up the fake objects
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        SecurityContextHolder.setContext(securityContext);
    }

    @Test
    void postTrip_TEST() {
        // create fake input data
        TripDTO inputDTO = new TripDTO();

        inputDTO.setDestination("Paris");

        // creating fake objects that the method will use
        Trip fakeTrip = new Trip();
        fakeTrip.setDestination("Paris");

        Driver fakeDriver = new Driver();
        fakeDriver.setEmail("driver@email.com");

        Trip fakeSavedTrip = new Trip();
        fakeSavedTrip.setId(1L);
        fakeSavedTrip.setDestination("Paris");

        TripDTO expectedResult = new TripDTO();
        expectedResult.setDestination("Paris");

        // tell our fake objects what to do when called

        // "when someone asks mapper to convert DTO to entity, give them fakeTrip"
        when(mapper.toEntity(inputDTO)).thenReturn(fakeTrip);

        // "when someone asks for authentication, give them fake authentication"
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn("driver@email.com");

        // "when someone looks for driver by email, give them fakeDriver"
        when(userRepository.findByEmail("driver@email.com")).thenReturn(fakeDriver);

        // "when someone saves a trip, give them fakeSavedTrip"
        when(repository.save(fakeTrip)).thenReturn(fakeSavedTrip);

        // "when someone asks mapper to convert entity to DTO, give them expectedResult"
        when(mapper.toDTO(fakeSavedTrip)).thenReturn(expectedResult);

        // run the method we'are testing
        TripDTO result = tripService.postTrip(inputDTO);

        // check if it worked correctly
        assertNotNull(result); // "Did we get something back?"
        assertEquals("Paris", result.getDestination()); // "Is the destination correct?"

        // checking that the fake methods were called
        verify(mapper).toEntity(inputDTO); // "Did it convert DTO to entity?"
        verify(userRepository).findByEmail("driver@email.com"); // "Did it look for the driver?"
        verify(repository).save(fakeTrip); // "Did it save the trip?"
        verify(mapper).toDTO(fakeSavedTrip); // "Did it convert back to DTO?"
    }
}