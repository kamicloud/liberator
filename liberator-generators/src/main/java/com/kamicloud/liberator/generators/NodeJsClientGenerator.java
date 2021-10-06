package com.kamicloud.liberator.generators;

import com.kamicloud.liberator.stubs.core.OutputStub;
import com.kamicloud.liberator.stubs.core.TemplateStub;
import com.kamicloud.liberator.generators.components.common.FileCombiner;
import com.kamicloud.liberator.generators.components.common.MultiLinesCombiner;
import org.thymeleaf.context.Context;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.Objects;

public class NodeJsClientGenerator extends BaseGenerator {

    protected File outputDir;

    @Override
    public String getName() {
        return "nodejs-client";
    }

    void setup() {
        outputDir = new File(Objects.requireNonNull(env.getProperty("generator.generators.nodejs-client.output")));

        outputDir.mkdirs();
    }

    @Override
    public void run() {
        this.setup();
        output.getTemplates().forEach(this::writeTemplate);

        writeTemplate("", output.getCurrentTemplate());
    }

    void writeTemplate(String version, TemplateStub templateStub) {
        FileCombiner file = new FileCombiner();

        file.setFileName(outputDir.getAbsolutePath() + "/API" + version + ".js");

        Locale locale = Locale.forLanguageTag("cn-zh");
        Context context = new Context(locale);
        context.setVariable("template", templateStub);
        String content = springTemplateEngine.process("js/api", context);

        file.addBlock(new MultiLinesCombiner(content));

        try {
            file.toFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
