package com.github.kamicloud.liberator.laravel.components;

import com.github.kamicloud.liberator.interfaces.CombinerInterface;
import com.github.kamicloud.liberator.stubs.core.EnumStub;

import java.util.ArrayList;

public class ClassConstantCombiner implements CombinerInterface {
    private String name;
    private String access;
    private ArrayList<String> lines = new ArrayList<>();
    private EnumStub.EnumStubItemType type;

    public ClassConstantCombiner(String name, EnumStub.EnumStubItemType type) {
        this(name, type, "public");
    }

    public ClassConstantCombiner(String name, EnumStub.EnumStubItemType type, String access) {
        this.name = name;
        this.type = type;
        this.access = access;
    }

    public void addLine(String line) {
        lines.add(line);
    }

    @Override
    public String toString() {
        String intend = "    ";
        String valueString = String.join("\n" + intend, lines);
        String prefix = intend + access + " const " + name + " = ";
        if (type != null) {
            if (type != EnumStub.EnumStubItemType.STRING) {
                return prefix + valueString + ";\n";
            } else {
                return prefix + "'" + valueString + "';\n";
            }
        }

        return prefix + valueString + ";\n";
    }
}
