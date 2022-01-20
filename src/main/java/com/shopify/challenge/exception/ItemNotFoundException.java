package com.shopify.challenge.exception;

public class ItemNotFoundException extends RuntimeException {

    public ItemNotFoundException(Long id) {
        super("Item with " + id + " not found");
    }

}

