package com.larrykin.ca1assignment.services;



import com.larrykin.ca1assignment.enums.OvenMode;
import com.larrykin.ca1assignment.interfaces.OvenOperations;
import com.larrykin.ca1assignment.models.Cake;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class OvenManager implements OvenOperations {
    private final Deque<Cake> oven;
    private OvenMode mode;
    private final int capacity;

    public OvenManager(int capacity, OvenMode mode) {
        this.oven = new ArrayDeque<>(capacity);
        this.capacity = capacity;
        this.mode = mode;
    }

    @Override
    public boolean addCake(Cake cake) {
        if (isFull()) {
            return false;
        }
        oven.push(cake);
        return true;
    }

    @Override
    public Cake removeCake() {
        if (isEmpty()) {
            return null;
        }

        // LIFO (Stack) - remove from the same end where we add
        if (mode == OvenMode.STACK) {
            return oven.pop();
        }
        // FIFO (Queue) - remove from the opposite end
        else {
            return oven.removeLast();
        }
    }

    @Override
    public Cake peekTopCake() {
        if (isEmpty()) {
            return null;
        }

        // LIFO (Stack) - peek the same end where we add
        if (mode == OvenMode.STACK) {
            return oven.peek();
        }
        // FIFO (Queue) - peek the opposite end
        else {
            return oven.peekLast();
        }
    }

    @Override
    public List<Cake> getAllCakes() {
        return new ArrayList<>(oven);
    }

    @Override
    public boolean isFull() {
        return oven.size() >= capacity;
    }

    @Override
    public boolean isEmpty() {
        return oven.isEmpty();
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

    @Override
    public int getCurrentSize() {
        return oven.size();
    }

    @Override
    public OvenMode getMode() {
        return mode;
    }

    @Override
    public void setMode(OvenMode mode) {
        this.mode = mode;
    }
}
