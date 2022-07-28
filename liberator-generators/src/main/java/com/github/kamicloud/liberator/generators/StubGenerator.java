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
            String templatePath = stub.getPath();

            if (stubName == GeneratorProperties.StubName.OUTPUT) {
                generateAll(templatePath, output);
            }

            if (stubName == GeneratorProperties.StubName.ERROR) {
                output.getErrors().forEach(errorStub -> {
                    generateAll(templatePath, errorStub);
                });
            }

            if (stubName == GeneratorProperties.StubName.TEMPLATE) {
                output.getTemplates().forEach((templateName, templateStub) -> {
                    generateAll(templatePath, templateStub);
                });
            }

            if (stubName == GeneratorProperties.StubName.ENUM) {
                output.getTemplates().forEach((templateName, template) -> {
                    template.getEnums().forEach(enumStub -> {
                        generateAll(templatePath, enumStub);
                    });
                });
            }

            if (stubName == GeneratorProperties.StubName.MODEL) {
                output.getTemplates().forEach((templateName, template) -> {
                    template.getModels().forEach(modelStub -> {
                        generateAll(templatePath, modelStub);
                    });
                });
            }

            if (stubName == GeneratorProperties.StubName.CONTROLLER) {
                output.getTemplates().forEach((templateName, template) -> {
                    template.getControllers().forEach(controllerStub -> {
                        generateAll(templatePath, controllerStub);
                    });
                });
            }

            if (stubName == GeneratorProperties.StubName.ACTION) {
                output.getTemplates().forEach((templateName, template) -> {
                    template.getControllers().forEach(controllerStub -> {
                        controllerStub.getActions().forEach(actionStub -> {
                            generateAll(templatePath, actionStub);
                        });
                    });
                });
            }

            if (stubName == GeneratorProperties.StubName.CURRENT_TEMPLATE) {
                generateAll(templatePath, output.getCurrentTemplate());
            }

            if (stubName == GeneratorProperties.StubName.CURRENT_TEMPLATE_ENUM) {
                output.getCurrentTemplate().getEnums().forEach(enumStub -> {
                    generateAll(templatePath, enumStub);
                });
            }

            if (stubName == GeneratorProperties.StubName.CURRENT_TEMPLATE_MODEL) {
                output.getCurrentTemplate().getModels().forEach(modelStub -> {
                    generateAll(templatePath, modelStub);
                });
            }

            if (stubName == GeneratorProperties.StubName.CURRENT_TEMPLATE_CONTROLLER) {
                output.getCurrentTemplate().getControllers().forEach(controllerStub -> {
                    generateAll(templatePath, controllerStub);
                });
            }

            if (stubName == GeneratorProperties.StubName.CURRENT_TEMPLATE_ACTION) {
                output.getCurrentTemplate().getControllers().forEach(controllerStub -> {
                    controllerStub.getActions().forEach(actionStub -> {
                        generateAll(templatePath, actionStub);
                    });
                });
            }
        });
    }

    public void generateAll(String templatePath, Object param) {
        String outputPath = this.stringUtil.renderTemplate(templatePath, param);

        this.generateTo(templatePath, outputPath, param);
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
