package com.kamicloud.liberator.generators.components;

import com.kamicloud.liberator.interfaces.CombinerInterface;

import java.util.LinkedList;

public class Combiner implements CombinerInterface {
    protected LinkedList<CombinerInterface> blocks = new LinkedList<>();

    public void addBlock(CombinerInterface block) {
        blocks.add(block);
    }

    public void addLine(String line) {
        if (line == null) {
            line = "";
        }
        blocks.add(new MultiLinesCombiner(line));
    }

    public void addLine() {
        addLine("");
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        blocks.forEach(block -> stringBuilder.append(block));

        return stringBuilder.toString();
    }

    public void addMultiLines(String... lines) {
        for (int i = 0; i < lines.length; i++) {
            addLine(lines[i]);
        }
    }
}
