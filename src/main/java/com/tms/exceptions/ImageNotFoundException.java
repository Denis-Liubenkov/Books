package com.tms.exceptions;

public class ImageNotFoundException extends RuntimeException {
    public ImageNotFoundException(String message, IllegalArgumentException e) {
        super(message);
    }
}