package com.kamicloud.liberator.interfaces;

public interface PHPNamespacePathTransformerInterface {
    String namespaceToPath(String namespace);
    String pathToNamespace(String path);
}
