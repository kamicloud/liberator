package com.kamicloud.liberator.stubs.core;

import com.kamicloud.liberator.parsers.DocParser;
import com.kamicloud.liberator.utils.UrlUtil;

import java.util.HashMap;
import java.util.LinkedList;

public class OutputStub {
    private HashMap<String, TemplateStub> templates = new HashMap<>();

    private TemplateStub currentTemplate;

    private final LinkedList<ErrorStub> errors = new LinkedList<>();

    public HashMap<String, TemplateStub> getTemplates() {
        return templates;
    }

    public HashMap<String, BaseWithAnnotationStub> classHashMap = new HashMap<>();

    public HashMap<String, ModelStub> modelHashMap = new HashMap<>();

    public void setTemplates(HashMap<String, TemplateStub> templates) {
        this.templates = templates;
    }

    public void addTemplate(TemplateStub templateStub) {
        this.templates.put(templateStub.getName(), templateStub);
    }

    /**
     * 模板分析完成后需要链接stub信息
     */
    public void postParse() {
        classHashMap.forEach((classpath, commentInterface) -> {
            String comment = DocParser.classDocHashMap.get(commentInterface.getClasspath());
            commentInterface.setComment(comment);
        });
        modelHashMap.forEach((classpath, modelStub) -> {
            modelStub.setParent(modelHashMap.get(modelStub.getParentClasspath()));
        });
        templates.forEach((version, templateStub) -> {
            templateStub.getControllers().forEach((controllerStub -> {
                controllerStub.getActions().forEach((action) -> {
                    String actionName = action.getName();

                    String uri = "/" + UrlUtil.transformVersion(version) +
                        "/" + UrlUtil.transformController(controllerStub.getName()) +
                        "/" + UrlUtil.transformAction(actionName);
                    action.setUri(uri);
                    action.setFullUri("/api" + uri);
                });
            }));
        });
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
