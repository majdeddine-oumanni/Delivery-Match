package com.delivery.backend.Repositories;

import com.delivery.backend.Model.PackageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PackageRequestRepository extends JpaRepository<PackageRequest, Long> {
}

