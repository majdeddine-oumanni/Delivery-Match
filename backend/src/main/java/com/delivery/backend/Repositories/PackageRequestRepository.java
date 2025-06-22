package com.delivery.backend.Repositories;

import com.delivery.backend.Model.PackageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PackageRequestRepository extends JpaRepository<PackageRequest, Long> {
    List<PackageRequest> findAllBySenderId(Long id);
}

