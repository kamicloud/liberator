package com.github.kamicloud.liberator.stubs.interfaces;

import com.github.kamicloud.liberator.stubs.core.AnnotationStub;

import java.lang.annotation.Annotation;

public interface AnnotationsInterface {
    void addAnnotation(Annotation annotation, AnnotationStub annotationStub);

    AnnotationStub getAnnotation(Class<?> type);
}
