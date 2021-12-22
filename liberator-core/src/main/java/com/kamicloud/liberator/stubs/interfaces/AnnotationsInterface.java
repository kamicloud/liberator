package com.kamicloud.liberator.stubs.interfaces;

import com.kamicloud.liberator.stubs.core.AnnotationStub;

import java.lang.annotation.Annotation;

public interface AnnotationsInterface {
    void addAnnotation(Annotation annotation, AnnotationStub annotationStub);

    AnnotationStub getAnnotation(Class<?> type);
}
