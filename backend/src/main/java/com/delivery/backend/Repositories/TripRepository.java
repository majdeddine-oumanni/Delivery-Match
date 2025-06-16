package com.delivery.backend.Repositories;

import com.delivery.backend.Model.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripRepository extends JpaRepository<Trip, Long> {
}
