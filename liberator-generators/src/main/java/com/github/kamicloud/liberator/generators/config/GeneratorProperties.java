package com.github.kamicloud.liberator.generators.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;

@ConfigurationProperties(prefix = "generator", ignoreUnknownFields = false)
@Component
public class GeneratorProperties {
    private Process process = new Process();
    private String env;
    private String apiPrefix;
    private String templatePath;
    private String testHost;
    private Generators generators = new Generators();

    private LinkedList<Stub> stubs = new LinkedList<>();

    public Process getProcess() {
        return process;
    }

    public void setProcess(Process process) {
        this.process = process;
    }

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }

    public String getApiPrefix() {
        return apiPrefix;
    }

    public void setApiPrefix(String apiPrefix) {
        this.apiPrefix = apiPrefix;
    }

    public String getTemplatePath() {
        return templatePath;
    }

    public void setTemplatePath(String templatePath) {
        this.templatePath = templatePath;
    }

    public String getTestHost() {
        return testHost;
    }

    public void setTestHost(String testHost) {
        this.testHost = testHost;
    }

    public Generators getGenerators() {
        return generators;
    }

    public void setGenerators(Generators generators) {
        this.generators = generators;
    }

    public static class Process {
        private ArrayList<String> laravelAutoTest;
        private ArrayList<String> client;

        public ArrayList<String> getLaravelAutoTest() {
            return laravelAutoTest;
        }

        public void setLaravelAutoTest(ArrayList<String> laravelAutoTest) {
            this.laravelAutoTest = laravelAutoTest;
        }

        public ArrayList<String> getClient() {
            return client;
        }

        public void setClient(ArrayList<String> client) {
            this.client = client;
        }
    }

    public static class Generators {
        private String forceSuffix;
        private Postman postman = new Postman();
        private Testcases testcases;

        public String getForceSuffix() {
            return forceSuffix;
        }

        public void setForceSuffix(String forceSuffix) {
            this.forceSuffix = forceSuffix;
        }

        public Postman getPostman() {
            return postman;
        }

        public void setPostman(Postman postman) {
            this.postman = postman;
        }

        public Testcases getTestcases() {
            return testcases;
        }

        public void setTestcases(Testcases testcases) {
            this.testcases = testcases;
        }

        public static class Postman {
            private String path;

            public String getPath() {
                return Objects.requireNonNull(path);
            }

            public void setPath(String path) {
                this.path = path;
            }
        }

        public static class Testcases {
            private String path;

            public String getPath() {
                return path;
            }

            public void setPath(String path) {
                this.path = path;
            }
        }
    }

    public LinkedList<Stub> getStubs() {
        return this.stubs;
    }

    public void setStubs(LinkedList<Stub> stubs) {
        this.stubs = stubs;
    }

    public static class Stub {
        private StubDriver driver;
        private StubName stub;
        private String template;
        private String path;

        public void setDriver(StubDriver driver) {
            this.driver = driver;
        }

        public StubDriver getDriver() {
            return this.driver;
        }

        public StubName getStub() {
            return this.stub;
        }

        public String getTemplate() {
            return this.template;
        }

        public void setStub(StubName stub) {
            this.stub = stub;
        }

        public void setTemplate(String template) {
            this.template = template;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public String getPath() {
            return this.path;
        }
    }

    public enum StubDriver {
        GENERATE,
        DELETE,
    }

    public enum StubName {
        OUTPUT,
        ERROR,
        TEMPLATE,
        CONSTANT,
        ENUM,
        MODEL,
        CONTROLLER,
        ACTION,
        CURRENT_TEMPLATE,
        CURRENT_TEMPLATE_CONSTANT,
        CURRENT_TEMPLATE_ENUM,
        CURRENT_TEMPLATE_MODEL,
        CURRENT_TEMPLATE_CONTROLLER,
        CURRENT_TEMPLATE_ACTION,
    }
}
