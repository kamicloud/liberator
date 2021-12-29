package com.github.kamicloud.liberator.stubs.core;

public class ErrorStub extends Stub {

    private String code;
    private String message;

    public ErrorStub(String name, String classpath, String code, String message) {
        super(name, classpath, null);

        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
