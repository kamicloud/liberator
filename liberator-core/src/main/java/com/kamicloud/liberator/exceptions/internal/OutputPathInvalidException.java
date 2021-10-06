package com.kamicloud.liberator.exceptions.internal;

public class OutputPathInvalidException extends RuntimeException {
    public OutputPathInvalidException(String path) {
        super("Output path is not able to write: " + path);
    }
}
