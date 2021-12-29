package com.github.kamicloud.liberator.laravel.interfaces;

public interface PHPNamespacePathTransformerInterface {
    String namespaceToPath(String namespace);
    String pathToNamespace(String path);
}
