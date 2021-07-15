package com.javaadvance.exceptions;


public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException(int id) {
        super("Computer with id " + id + " isn't found");
    }
}
