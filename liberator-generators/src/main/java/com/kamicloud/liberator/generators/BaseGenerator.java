package com.kamicloud.liberator.generators;

import com.kamicloud.liberator.generators.config.GeneratorProperties;
import com.kamicloud.liberator.stubs.core.OutputStub;
import com.kamicloud.liberator.utils.StringUtil;
import com.kamicloud.liberator.utils.UrlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.thymeleaf.spring5.SpringTemplateEngine;

import java.io.File;
import java.util.*;

@Component
abstract class BaseGenerator {
    File dir = new File("");
    @Autowired
    protected SpringTemplateEngine springTemplateEngine;

    @Autowired
    protected Environment env;

    @Autowired
    protected UrlUtil urlUtil;

    @Autowired
    protected StringUtil stringUtil;

    @Autowired
    GeneratorProperties generatorProperties;

    protected HashMap<String, String> processes = new HashMap<>();

    public void updatex(OutputStub o) {
        String name = getName();
        ArrayList<String> generators;
        GeneratorProperties.Process process = generatorProperties.getProcess();

        if (env.getProperty("process", "").equals("laravel-auto-test")) {
            generators = process.getLaravelAutoTest();
        } else {
            generators = process.getDefaults();
        }

        if (generators == null || !generators.contains(name)) {
            return;
        }

        postConstruct();
        update((OutputStub) o);
    }

    abstract String getName();

    abstract void postConstruct();

    public abstract void update(OutputStub o);
}
