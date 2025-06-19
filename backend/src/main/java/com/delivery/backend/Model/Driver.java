package com.delivery.backend.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Driver extends User{
    @OneToMany(mappedBy = "driver")
    private List<Trip> trips = new ArrayList<>();
}
