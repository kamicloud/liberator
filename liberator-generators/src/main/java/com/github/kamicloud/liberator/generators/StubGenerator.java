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

            if (stubName == GeneratorProperties.StubName.CURRENT_TEMPLATE_ENUM) {
                output.getTemplates().forEach((templateName, template) -> {
                    template.getEnums().forEach(enumStub -> {
                        String outputPath = this.stringUtil.renderTemplate(templatePath + "_path", enumStub);

                        this.generateTo(templatePath, outputPath, enumStub);
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
