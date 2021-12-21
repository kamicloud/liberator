package com.kamicloud.liberator;

import com.kamicloud.liberator.config.LiberatorProperties;
import com.kamicloud.liberator.parsers.ASTParser;
import com.kamicloud.liberator.parsers.DocParser;
import com.kamicloud.liberator.parsers.BuildParser;
import com.kamicloud.liberator.stubs.core.OutputStub;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedHashMap;
import java.util.LinkedList;

public class LiberatorManager {
    private final static LinkedHashMap<String, Generator> generators = new LinkedHashMap<>();

    @Autowired
    LiberatorProperties liberatorProperties;

    @Autowired
    private OutputStub output;

    @Autowired
    private BuildParser buildParser;

    @Autowired
    private DocParser docParser;

    @Autowired
    private ASTParser astParser;

    public void run() {
        buildParser.parse();
        docParser.parse();
        astParser.parse();

        output.postParse();

        LinkedList<String> enabledGenerators = this.liberatorProperties.getGenerators();

        enabledGenerators.forEach(enabledGenerator -> {
            generators.get(enabledGenerator).run();
        });
    }

    public static void addGenerator(Generator generator) {
        generators.put(generator.getName(), generator);
    }
}
