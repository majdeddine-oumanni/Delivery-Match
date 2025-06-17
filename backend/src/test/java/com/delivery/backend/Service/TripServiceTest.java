package com.delivery.backend.Service;

import com.delivery.backend.Repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

class TripServiceTest {

    @Mock
    private UserRepository repository;

    @InjectMocks
    private UserService service;


    @Test
    void postTrip() {

    }
}