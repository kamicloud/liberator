package com.kamicloud.liberator.stubs.core;

import com.kamicloud.liberator.parsers.DocParser;

import java.util.HashMap;
import java.util.LinkedList;

public class OutputStub {
    protected final LinkedList<ErrorStub> errors = new LinkedList<>();
    protected final HashMap<String, TemplateStub> templates = new HashMap<>();

    protected TemplateStub currentTemplate;

    public final HashMap<String, Stub> classHashMap = new HashMap<>();
    public final HashMap<String, ModelStub> modelHashMap = new HashMap<>();

    public void postParse() {
        // add comments to stubs
        classHashMap.forEach((classpath, commentInterface) -> {
            String comment = DocParser.classDocHashMap.get(commentInterface.getClasspath());

            commentInterface.setComment(comment);
        });

        // model extends
        modelHashMap.forEach((classpath, modelStub) -> {
            modelStub.setExtend(modelHashMap.get(modelStub.getExtendClasspath()));
        });
    }

    public HashMap<String, TemplateStub> getTemplates() {
        return templates;
    }

    public void addTemplate(TemplateStub templateStub) {
        this.templates.put(templateStub.getName(), templateStub);
    }

    public void addError(ErrorStub errorStub) {
        errors.add(errorStub);
    }

    public LinkedList<ErrorStub> getErrors() {
        return errors;
    }

    public TemplateStub getCurrentTemplate() {
        return currentTemplate;
    }

    public void setCurrentTemplate(TemplateStub currentTemplate) {
        this.currentTemplate = currentTemplate;
        currentTemplate.setCurrent(true);
    }
}
