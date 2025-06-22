package com.delivery.backend.Service;

import com.delivery.backend.DTO.PackageRequestDTO;
import com.delivery.backend.Mappers.PackageRequestMapper;
import com.delivery.backend.Model.PackageRequest;
import com.delivery.backend.Model.RequestStatus;
import com.delivery.backend.Model.Sender;
import com.delivery.backend.Model.Trip;
import com.delivery.backend.Repositories.PackageRequestRepository;
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

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class PackageRequestServiceTest {
    @Mock
    private PackageRequestRepository repository;

    @Mock
    private PackageRequestMapper mapper;

    @Mock
    private UserRepository senderRepository;

    @Mock
    private TripRepository tripRepository;

    @Mock
    private SecurityContext securityContext;

    @Mock
    private Authentication authentication;

    @InjectMocks
    private PackageRequestService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        SecurityContextHolder.setContext(securityContext);
    }

    @Test
    void createRequest() {
        PackageRequestDTO inputDTO = new PackageRequestDTO();
        inputDTO.setTripId(1L);
        inputDTO.setWeight(5.0);
        inputDTO.setHeight(30);
        inputDTO.setLength(44);
        inputDTO.setDescription("Test package");
        inputDTO.setStatus(RequestStatus.PENDING);

        PackageRequest fakeEntity = new PackageRequest();
        fakeEntity.setTripId(1L);
        fakeEntity.setWeight(5.0);

        Trip fakeTrip = new Trip();
        fakeTrip.setId(1L);

        Sender fakeSender = new Sender();
        fakeSender.setEmail("test@email.com");

        PackageRequest fakeSavedEntity = new PackageRequest();
        fakeSavedEntity.setId(1L);
        fakeSavedEntity.setTripId(1L);

        PackageRequestDTO expectedOutput = new PackageRequestDTO();
        expectedOutput.setTripId(1L);
        expectedOutput.setWeight(5.0);

        when(mapper.toEntity(inputDTO)).thenReturn(fakeEntity);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn("test@email.com");
        when(tripRepository.findById(1L)).thenReturn(Optional.of(fakeTrip));
        when(senderRepository.findByEmail("test@email.com")).thenReturn(fakeSender);
        when(repository.save(any(PackageRequest.class))).thenReturn(fakeSavedEntity);
        when(mapper.toDTO(fakeSavedEntity)).thenReturn(expectedOutput);

        PackageRequestDTO result = service.createRequest(inputDTO);

        assertNotNull(result);
        assertEquals(1L, result.getTripId());
        assertEquals(5.0, result.getWeight());

        verify(mapper).toEntity(inputDTO);
        verify(tripRepository).findById(1L);
        verify(senderRepository).findByEmail("test@email.com");
        verify(repository).save(any(PackageRequest.class));
        verify(mapper).toDTO(fakeSavedEntity);
    }
}



















