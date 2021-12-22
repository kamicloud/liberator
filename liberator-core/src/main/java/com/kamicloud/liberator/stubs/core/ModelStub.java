package com.kamicloud.liberator.stubs.core;

import com.kamicloud.liberator.interfaces.ResourceInterface;
import definitions.annotations.RESTFul;

import java.util.LinkedList;

public class ModelStub extends Stub implements ResourceInterface {
    protected final LinkedList<ParameterStub> parameters = new LinkedList<>();
    protected String extendClasspath;
    protected ModelStub extend;

    public ModelStub(String name, String classpath, TemplateStub parent) {
        super(name, classpath, parent);
    }

    public LinkedList<ParameterStub> getParameters() {
        LinkedList<ParameterStub> parameters = new LinkedList<>();
        if (extend != null) {
            parameters.addAll(extend.getParameters());
        }

        this.parameters.forEach(parameterStub -> {
            parameters.remove(parameterStub);
            parameters.add(parameterStub);
        });

        return parameters;
    }

    public void setExtendClasspath(String extendClasspath) {
        this.extendClasspath = extendClasspath;
    }

    public String getExtendClasspath() {
        return extendClasspath;
    }

    public void setExtend(ModelStub extend) {
        this.extend = extend;
    }

    public void addParameter(ParameterStub parameterStub) {
        parameters.add(parameterStub);
    }

    public boolean isResource() {
        return hasAnnotation(RESTFul.class);
    }

    @Override
    public LinkedList<ParameterStub> clone() {

        return new LinkedList<>(this.parameters);
    }
}
