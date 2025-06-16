package com.delivery.backend.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String start;
    private List<String> wayPoints = new ArrayList<>();
    private String destination;
    private int maxPackageCapacity;
    private String typeOfGoods;

    @ManyToOne
    private Driver driver;
}
