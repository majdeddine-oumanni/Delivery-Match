package com.delivery.backend.Repositories;

import com.delivery.backend.Model.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TripRepository extends JpaRepository<Trip, Long> {
    List<Trip> findByDriver_Id(Long id);
}
