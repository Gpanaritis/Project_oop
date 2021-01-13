package com.company;

class NotEnoughStock extends Exception {
    public NotEnoughStock(String message) {
        super(message);
    }
}