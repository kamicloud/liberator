package com.github.kamicloud.liberator.stubs.core;

import java.util.LinkedList;

public class ActionStub extends Stub {
    protected final LinkedList<ParameterStub> requests = new LinkedList<>();
    protected final LinkedList<ParameterStub> responses = new LinkedList<>();

    protected final ControllerStub controller;

    public ActionStub(String name, String classpath, ControllerStub parent) {
        super(name, classpath, parent);

        this.controller = parent;
    }

    public LinkedList<ParameterStub> getRequests() {
        return requests;
    }

    public LinkedList<ParameterStub> getResponses() {
        return responses;
    }

    public void addRequest(ParameterStub parameterStub) {
        requests.add(parameterStub);
    }

    public void addResponse(ParameterStub parameterStub) {
        responses.add(parameterStub);
    }

    public ControllerStub getController() {
        return controller;
    }
}
