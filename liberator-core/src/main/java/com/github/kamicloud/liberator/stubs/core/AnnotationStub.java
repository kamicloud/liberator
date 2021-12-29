package com.github.kamicloud.liberator.stubs.core;

import java.util.ArrayList;

public class AnnotationStub extends Stub {
    protected String value = "";
    protected final ArrayList<String> values = new ArrayList<>();

    public AnnotationStub(String name, Stub parent) {
        super(name, null, parent);
    }

    public String getName() {
        return name;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void addValue(String value) {
        this.values.add(value);
    }

    public ArrayList<String> getValues() {
        return values;
    }

    public String getValue() {
        return value;
    }
}
