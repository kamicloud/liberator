package com.kamicloud.liberator.laravel.components;

import com.kamicloud.liberator.interfaces.CombinerInterface;
import com.kamicloud.liberator.components.FileCombiner;
import com.kamicloud.liberator.components.Combiner;
import com.kamicloud.liberator.interfaces.FileCombinerInterface;

import java.io.IOException;

public class PHPFileCombiner extends Combiner implements FileCombinerInterface, CombinerInterface {
    protected String fileName;

    @Override
    public String toString() {
        return "<?php\n\n" + super.toString();
    }

    @Override
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void toFile() throws IOException {
        FileCombiner fileCombiner = new FileCombiner();

        fileCombiner.setFileName(fileName);
        fileCombiner.addBlock(this);

        fileCombiner.toFile();
    }
}
