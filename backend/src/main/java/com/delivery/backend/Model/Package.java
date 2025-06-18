package com.delivery.backend.Model;

import jakarta.persistence.Embedded;

public class Package {
    private double weight; // in kg
    private double length; // in cm
    private double width; // in cm
    private double height; // in cm
    private double volume; // calculated field (l*w*h)
}
