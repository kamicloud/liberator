package com.kamicloud.liberator.interfaces;

import java.io.IOException;

public interface FileCombinerInterface {

    void setFileName(String fileName);

    void toFile() throws IOException;
}
