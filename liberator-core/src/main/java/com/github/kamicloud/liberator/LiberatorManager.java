package com.github.kamicloud.liberator;

import com.github.kamicloud.liberator.config.LiberatorProperties;
import com.github.kamicloud.liberator.parsers.ASTParser;
import com.github.kamicloud.liberator.parsers.DocParser;
import com.github.kamicloud.liberator.parsers.BuildParser;
import com.github.kamicloud.liberator.stubs.core.OutputStub;
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
            Generator generator = generators.get(enabledGenerator);

            if (generator == null) {
                System.out.println(enabledGenerator + " not found!");

                return;
            }

            generator.run();
        });
    }

    public static void addGenerator(Generator generator) {
        generators.put(generator.getName(), generator);
    }
}
