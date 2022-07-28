package com.github.kamicloud.liberator.generators;

import com.github.kamicloud.liberator.components.FileCombiner;
import com.github.kamicloud.liberator.generators.config.GeneratorProperties;
import com.github.kamicloud.liberator.utils.FileUtil;

import java.io.IOException;
import java.util.Arrays;

public class StubGenerator extends BaseGenerator {
    @Override
    public String getName() {
        return "stub";
    }

    @Override
    public void run() {
        this.generatorProperties.getStubs().forEach(stub -> {
            GeneratorProperties.StubDriver driver = stub.getDriver();
            GeneratorProperties.StubName stubName = stub.getStub();
            String templatePath = stub.getTemplate();
            String path = stub.getPath();

            if (driver == GeneratorProperties.StubDriver.DELETE) {
                FileUtil.deleteAllFilesOfDir(path);
            }

            if (driver == GeneratorProperties.StubDriver.GENERATE) {
                generateTask(stubName, templatePath);
            }
        });
    }

    public void generateTask(GeneratorProperties.StubName stubName, String templatePath) {
        if (stubName == GeneratorProperties.StubName.OUTPUT) {
            generateTo(templatePath, output);
        }

        if (stubName == GeneratorProperties.StubName.ERROR) {
            output.getErrors().forEach(errorStub -> {
                generateTo(templatePath, errorStub);
            });
        }

        if (stubName == GeneratorProperties.StubName.TEMPLATE) {
            output.getTemplates().forEach((templateName, templateStub) -> {
                generateTo(templatePath, templateStub);
            });
        }

        if (stubName == GeneratorProperties.StubName.ENUM) {
            output.getTemplates().forEach((templateName, template) -> {
                template.getEnums().forEach(enumStub -> {
                    generateTo(templatePath, enumStub);
                });
            });
        }

        if (stubName == GeneratorProperties.StubName.MODEL) {
            output.getTemplates().forEach((templateName, template) -> {
                template.getModels().forEach(modelStub -> {
                    generateTo(templatePath, modelStub);
                });
            });
        }

        if (stubName == GeneratorProperties.StubName.CONTROLLER) {
            output.getTemplates().forEach((templateName, template) -> {
                template.getControllers().forEach(controllerStub -> {
                    generateTo(templatePath, controllerStub);
                });
            });
        }

        if (stubName == GeneratorProperties.StubName.ACTION) {
            output.getTemplates().forEach((templateName, template) -> {
                template.getControllers().forEach(controllerStub -> {
                    controllerStub.getActions().forEach(actionStub -> {
                        generateTo(templatePath, actionStub);
                    });
                });
            });
        }

        if (stubName == GeneratorProperties.StubName.CURRENT_TEMPLATE) {
            generateTo(templatePath, output.getCurrentTemplate());
        }

        if (stubName == GeneratorProperties.StubName.CURRENT_TEMPLATE_ENUM) {
            output.getCurrentTemplate().getEnums().forEach(enumStub -> {
                generateTo(templatePath, enumStub);
            });
        }

        if (stubName == GeneratorProperties.StubName.CURRENT_TEMPLATE_MODEL) {
            output.getCurrentTemplate().getModels().forEach(modelStub -> {
                generateTo(templatePath, modelStub);
            });
        }

        if (stubName == GeneratorProperties.StubName.CURRENT_TEMPLATE_CONTROLLER) {
            output.getCurrentTemplate().getControllers().forEach(controllerStub -> {
                generateTo(templatePath, controllerStub);
            });
        }

        if (stubName == GeneratorProperties.StubName.CURRENT_TEMPLATE_ACTION) {
            output.getCurrentTemplate().getControllers().forEach(controllerStub -> {
                controllerStub.getActions().forEach(actionStub -> {
                    generateTo(templatePath, actionStub);
                });
            });
        }
    }

    public void generateTo(String stubPath, Object param) {
        try {
            String value = this.stringUtil.renderTemplate(stubPath, param);
            String[] contents = value.split("\n");

            FileCombiner.build(contents[0], String.join("\n", Arrays.copyOfRange(contents, 1, contents.length)), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
