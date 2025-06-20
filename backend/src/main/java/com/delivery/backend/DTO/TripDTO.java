package com.delivery.backend.DTO;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class TripDTO {
    private String start;
    private List<String> wayPoints = new ArrayList<>();
    private String destination;
    private int maxPackageCapacity;
    private String typeOfGoods;

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public List<String> getWayPoints() {
        return wayPoints;
    }

    public void setWayPoints(List<String> wayPoints) {
        this.wayPoints = wayPoints;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getMaxPackageCapacity() {
        return maxPackageCapacity;
    }

    public void setMaxPackageCapacity(int maxPackageCapacity) {
        this.maxPackageCapacity = maxPackageCapacity;
    }

    public String getTypeOfGoods() {
        return typeOfGoods;
    }

    public void setTypeOfGoods(String typeOfGoods) {
        this.typeOfGoods = typeOfGoods;
    }

}
