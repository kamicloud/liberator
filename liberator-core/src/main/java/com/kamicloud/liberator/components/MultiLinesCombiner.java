package com.kamicloud.liberator.components;

import com.kamicloud.liberator.interfaces.CombinerInterface;

import java.util.Arrays;
import java.util.LinkedList;

public class MultiLinesCombiner extends Combiner implements CombinerInterface {
    private final LinkedList<String> blocks;

    public MultiLinesCombiner(String ...blocks) {
        this.blocks = new LinkedList<>(Arrays.asList(blocks));
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        blocks.forEach(block -> stringBuilder.append(block).append("\n"));

        return stringBuilder.toString();
    }

    public void addLine(String line) {
        blocks.add(line);
    }
}
