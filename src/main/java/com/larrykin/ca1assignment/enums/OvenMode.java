package com.larrykin.ca1assignment.enums;


public enum OvenMode {
    STACK("Stack (LIFO) - Front door for both adding and removing"),
    QUEUE("Queue (FIFO) - Front door for adding, opposite door for removing");

    private final String description;

    OvenMode(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}