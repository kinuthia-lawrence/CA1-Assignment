package com.larrykin.ca1assignment.interfaces;

import com.larrykin.ca1assignment.enums.OvenMode;
import com.larrykin.ca1assignment.models.Cake;

import java.util.List;

public interface OvenOperations {
    boolean addCake(Cake cake);
    Cake removeCake();
    Cake peekTopCake();
    List<Cake> getAllCakes();
    boolean isFull();
    boolean isEmpty();
    int getCapacity();
    int getCurrentSize();
    OvenMode getMode();
    void setMode(OvenMode mode);
}
