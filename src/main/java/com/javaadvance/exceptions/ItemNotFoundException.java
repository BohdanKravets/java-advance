package com.javaadvance.exceptions;

public class ItemNotFoundException extends RuntimeException {

    public ItemNotFoundException(String item, int id) {
        super(String.format("%s with id %o is not found", item, id));

    }
}
