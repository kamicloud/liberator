package com.kamicloud.liberator;

import com.kamicloud.liberator.stubs.core.OutputStub;
import com.kamicloud.liberator.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.thymeleaf.spring5.SpringTemplateEngine;

@Component
public abstract class Generator {
    @Autowired
    protected OutputStub output;

    @Autowired
    protected SpringTemplateEngine springTemplateEngine;

    @Autowired
    protected Environment env;

    @Autowired
    protected StringUtil stringUtil;

    public Generator() {
        LiberatorManager.addGenerator(this);
    }

    public abstract String getName();

    public abstract void run();
}
