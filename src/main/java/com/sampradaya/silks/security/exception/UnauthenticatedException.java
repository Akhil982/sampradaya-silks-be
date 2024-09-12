package com.thespot.bookings.security.exception;

public class UnauthenticatedException extends RuntimeException {
    public UnauthenticatedException(String msg) {
        super(msg);
    }
}
