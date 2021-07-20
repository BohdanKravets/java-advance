package com.javaadvance.exceptions;

public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException(String entity, int id) {
        super(String.format("No item found for %s with id %s", entity,id));

    }
}
