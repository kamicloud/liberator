package com.kamicloud.liberator.imba;

import com.kamicloud.liberator.config.ApplicationProperties;
import com.kamicloud.liberator.config.DefaultProfileUtil;
import com.kamicloud.liberator.parsers.DocParser;
import com.kamicloud.liberator.parsers.Parser;
import com.kamicloud.liberator.stubs.core.OutputStub;
import com.kamicloud.liberator.generators.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.env.Environment;
import org.springframework.boot.SpringApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableConfigurationProperties({ApplicationProperties.class})
public class Main {


    private static final Logger log = LoggerFactory.getLogger(Main.class);

    private Environment env;
    private PostmanGenerator postmanWriter;
    private LaravelGenerator laravelWriter;
    private TestCaseGenerator testCaseGenerator;
    private DocGenerator docWriter;
    private AutoTestGenerator autoTestWriter;
    private NodeJsClientGenerator nodeJsClientWriter;
    private OpenAPIGenerator openAPIWriter;
    private Parser parser;
    private DocParser docParser;
    private OutputStub output;

    @Autowired
    public void Generator(
        TestCaseGenerator testCaseGenerator,
        Environment env,
        PostmanGenerator postmanWriter,
        LaravelGenerator laravelWriter,
        DocGenerator docWriter,
        AutoTestGenerator autoTestWriter,
        NodeJsClientGenerator nodeJsClientWriter,
        OpenAPIGenerator openAPIWriter,
        Parser parser,
        DocParser docParser,
        OutputStub output
    ) {
        this.testCaseGenerator = testCaseGenerator;
        this.env = env;
        this.postmanWriter = postmanWriter;
        this.laravelWriter = laravelWriter;
        this.docWriter = docWriter;
        this.autoTestWriter = autoTestWriter;
        this.nodeJsClientWriter = nodeJsClientWriter;
        this.openAPIWriter = openAPIWriter;
        this.parser = parser;
        this.docParser = docParser;
        this.output = output;
    }

    public Main() {
    }

    @PostConstruct
    public void initApplication() {
        log.debug("logger start");
        DefaultProfileUtil.setEnv(env);

        // 解析模板和注释
        parser.parse();
        docParser.parse();

        // 分析结束同步数据
        output.postParse();

        // 注册处理器
        postmanWriter.updatex(output);
        testCaseGenerator.updatex(output);
        docWriter.updatex(output);
        laravelWriter.updatex(output);
        nodeJsClientWriter.updatex(output);
        autoTestWriter.updatex(output);
        openAPIWriter.updatex(output);
    }

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Main.class);
        DefaultProfileUtil.addDefaultProfile(app);
        app.run(args);
    }
}
