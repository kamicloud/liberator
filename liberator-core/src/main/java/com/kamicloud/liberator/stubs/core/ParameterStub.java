package com.kamicloud.liberator.stubs.core;

import definitions.types.Type;
import definitions.official.TypeSpec;

public class ParameterStub extends Stub {
    protected String typeSimpleName;

    protected boolean array = false;

    protected int arrayDepth = 0;

    protected Type type;

    protected String typeClasspath;

    public ParameterStub(
        String name,
        String classpath,
        Stub parent,
        String type,
        String typeClasspath
    ) {
        super(name, classpath, parent);

        this.typeSimpleName = type;
        this.typeClasspath = typeClasspath;
    }

    public void setArrayDepth(int depth) {
        this.arrayDepth = depth;
    }

    public boolean isArray() {
        return arrayDepth > 0;
    }

    public boolean isModel() {
        return type.getSpec() == TypeSpec.MODEL;
    }

    public boolean isEnum() {
        return type.getSpec() == TypeSpec.ENUM;
    }

    public boolean isBoolean() {
        return type.getSpec() == TypeSpec.BOOLEAN;
    }

    public String getTypeSimpleName() {
        return typeSimpleName;
    }

    public TypeSpec getTypeSpec() {
        return type.getSpec();
    }

    public String getTypeComment() {
        return type.getComment();
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    @Override
    public boolean equals(Object obj) {
        return ((ParameterStub) obj).getName().equals(this.getName());
    }
}
