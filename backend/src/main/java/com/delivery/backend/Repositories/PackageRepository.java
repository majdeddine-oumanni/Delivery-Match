package com.delivery.backend.Repositories;

import com.delivery.backend.Model.Package;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PackageRepository extends JpaRepository<Package, Long> {
    List<Package> findBySenderId(Long senderId);
}
