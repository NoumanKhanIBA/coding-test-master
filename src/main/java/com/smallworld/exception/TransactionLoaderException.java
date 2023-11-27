package com.smallworld.exception;

public class TransactionLoaderException extends RuntimeException {
    public TransactionLoaderException(String message) {
        super(message);
    }

    public TransactionLoaderException(String message, Throwable cause) {
        super(message, cause);
    }
}
