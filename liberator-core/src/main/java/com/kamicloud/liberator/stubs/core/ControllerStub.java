package com.kamicloud.liberator.stubs.core;

import java.util.LinkedList;

public class ControllerStub extends Stub {
    protected final LinkedList<ActionStub> actions = new LinkedList<>();

    public ControllerStub(String name, String classpath, TemplateStub parent) {
        super(name, classpath, parent);
    }

    public LinkedList<ActionStub> getActions() {
        return actions;
    }

    public void addAction(ActionStub actionStub) {
        actions.add(actionStub);
    }
}
