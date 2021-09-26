package com.kamicloud.liberator.generators;

import com.kamicloud.liberator.Generator;
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

abstract class BaseGenerator extends Generator {
    File dir = new File("");

    @Autowired
    protected UrlUtil urlUtil;

    @Autowired
    protected StringUtil stringUtil;

    @Autowired
    GeneratorProperties generatorProperties;

    protected HashMap<String, String> processes = new HashMap<>();
}
