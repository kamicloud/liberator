package com.github.kamicloud.liberator.generators;

import com.github.kamicloud.liberator.components.FileCombiner;
import com.github.kamicloud.liberator.generators.config.GeneratorProperties;

import java.io.IOException;

public class StubGenerator extends BaseGenerator {
    @Override
    public String getName() {
        return "stub";
    }

    @Override
    public void run() {
        this.generatorProperties.getStubs().forEach(stub -> {
            GeneratorProperties.StubName stubName = stub.getStub();
            String templatePath = stub.getTemplate();
            String outputPath = stub.getPath();

            if (stubName == GeneratorProperties.StubName.OUTPUT) {
                generateTo(templatePath, outputPath, output);
            }

            if (stubName == GeneratorProperties.StubName.ERROR) {
                output.getErrors().forEach(errorStub -> {
                    generateTo(templatePath, outputPath, errorStub);
                });
            }

            if (stubName == GeneratorProperties.StubName.TEMPLATE) {
                output.getTemplates().forEach((templateName, templateStub) -> {
                    generateTo(templatePath, outputPath, templateStub);
                });
            }

            if (stubName == GeneratorProperties.StubName.ENUM) {
                output.getTemplates().forEach((templateName, template) -> {
                    template.getEnums().forEach(enumStub -> {
                        generateTo(templatePath, outputPath, enumStub);
                    });
                });
            }

            if (stubName == GeneratorProperties.StubName.MODEL) {
                output.getTemplates().forEach((templateName, template) -> {
                    template.getModels().forEach(modelStub -> {
                        generateTo(templatePath, outputPath, modelStub);
                    });
                });
            }

            if (stubName == GeneratorProperties.StubName.CONTROLLER) {
                output.getTemplates().forEach((templateName, template) -> {
                    template.getControllers().forEach(controllerStub -> {
                        generateTo(templatePath, outputPath, controllerStub);
                    });
                });
            }

            if (stubName == GeneratorProperties.StubName.ACTION) {
                output.getTemplates().forEach((templateName, template) -> {
                    template.getControllers().forEach(controllerStub -> {
                        controllerStub.getActions().forEach(actionStub -> {
                            generateTo(templatePath, outputPath, actionStub);
                        });
                    });
                });
            }

            if (stubName == GeneratorProperties.StubName.CURRENT_TEMPLATE) {
                generateTo(templatePath, outputPath, output.getCurrentTemplate());
            }

            if (stubName == GeneratorProperties.StubName.CURRENT_TEMPLATE_ENUM) {
                output.getCurrentTemplate().getEnums().forEach(enumStub -> {
                    generateTo(templatePath, outputPath, enumStub);
                });
            }

            if (stubName == GeneratorProperties.StubName.CURRENT_TEMPLATE_MODEL) {
                output.getCurrentTemplate().getModels().forEach(modelStub -> {
                    generateTo(templatePath, outputPath, modelStub);
                });
            }

            if (stubName == GeneratorProperties.StubName.CURRENT_TEMPLATE_CONTROLLER) {
                output.getCurrentTemplate().getControllers().forEach(controllerStub -> {
                    generateTo(templatePath, outputPath, controllerStub);
                });
            }

            if (stubName == GeneratorProperties.StubName.CURRENT_TEMPLATE_ACTION) {
                output.getCurrentTemplate().getControllers().forEach(controllerStub -> {
                    controllerStub.getActions().forEach(actionStub -> {
                        generateTo(templatePath, outputPath, actionStub);
                    });
                });
            }
        });
    }

    public void generateTo(String stubPath, String outputPath, Object param) {
        try {
            String value = this.stringUtil.renderTemplate(stubPath, param);

            FileCombiner.build(outputPath, value, true);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
