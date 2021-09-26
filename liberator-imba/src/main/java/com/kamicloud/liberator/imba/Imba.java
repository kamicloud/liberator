package com.kamicloud.liberator.imba;

import com.kamicloud.liberator.LiberatorManager;
import com.kamicloud.liberator.imba.config.ApplicationProperties;
import com.kamicloud.liberator.config.DefaultProfileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.env.Environment;
import org.springframework.boot.SpringApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication(scanBasePackages = {"com.kamicloud.liberator"})
@EnableConfigurationProperties({ApplicationProperties.class})
public class Imba {
    private static final Logger log = LoggerFactory.getLogger(Imba.class);

    private Environment env;
    private static LiberatorManager liberatorManager;

    @Autowired
    public void Generator(
        Environment env,
        LiberatorManager liberatorManager
    ) {
        this.env = env;

        Imba.liberatorManager = liberatorManager;
    }

    @PostConstruct
    public void initApplication() {
        log.debug("logger start");
        DefaultProfileUtil.setEnv(env);
    }

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Imba.class);
        DefaultProfileUtil.addDefaultProfile(app);
        app.run(args);

        Imba.liberatorManager.run();
    }
}
