package com.dawid.calculator.utils;

/**
 * Created by Dawid Świnoga on 16.03.2017.
 */

public class UnknownSignException extends Exception {
    public UnknownSignException() {
    }

    public UnknownSignException(String message) {
        super(message);
    }
}
