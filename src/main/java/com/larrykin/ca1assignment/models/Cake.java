package com.larrykin.ca1assignment.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Cake {
    private String name;
    private double weight;
    private LocalDate bestBeforeDate;
    private LocalDateTime timeAddedToOven;

    public Cake(String name, double weight, LocalDate bestBeforeDate) {
        this.name = name;
        this.weight = weight;
        this.bestBeforeDate = bestBeforeDate;
        this.timeAddedToOven = LocalDateTime.now();
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }

    public LocalDate getBestBeforeDate() {
        return bestBeforeDate;
    }

    public LocalDateTime getTimeAddedToOven() {
        return timeAddedToOven;
    }

    @Override
    public String toString() {
        return name + " - " + weight + "g (Best before: " + bestBeforeDate + ")";
    }
}