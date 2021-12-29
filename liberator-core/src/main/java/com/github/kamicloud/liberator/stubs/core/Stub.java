package com.github.kamicloud.liberator.stubs.core;

import com.github.kamicloud.liberator.utils.CommentUtil;
import com.github.kamicloud.liberator.utils.StringUtil;
import com.google.common.base.CaseFormat;
import com.github.kamicloud.liberator.stubs.interfaces.AnnotationsInterface;
import com.github.kamicloud.liberator.stubs.interfaces.CommentInterface;
import definitions.annotations.Extendable;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

@SuppressWarnings("unused")
public abstract class Stub implements AnnotationsInterface, CommentInterface {
    protected String classpath;

    protected final Stub parent;
    protected final HashMap<String, AnnotationStub> annotations = new HashMap<>();
    protected final ArrayList<String> comments = new ArrayList<>();
    protected String comment;

    protected String name;
    protected String upperCamelName;
    protected String lowerCamelName;
    protected String lowerUnderScoreName;
    protected String upperUnderScoreName;

    public Stub(
        String name,
        String classpath,
        Stub parent
    ) {
        this.name = name;
        this.upperCamelName = name;
        this.lowerCamelName = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, name);
        this.lowerUnderScoreName = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, name);
        this.upperUnderScoreName = CaseFormat.UPPER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, name);

        this.classpath = classpath;

        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public String getUpperCamelName() {
        return upperCamelName;
    }

    public String getLowerCamelName() {
        return lowerCamelName;
    }

    public String getLowerUnderScoreName() {
        return lowerUnderScoreName;
    }

    public String getUpperUnderScoreName() {
        return upperUnderScoreName;
    }

    public void addAnnotation(Annotation type, AnnotationStub annotationStub) {
        annotations.put(type.annotationType().getName(), annotationStub);
    }

    @Override
    public String getClasspath() {
        return classpath;
    }

    @Override
    public void setClasspath(String classpath) {
        this.classpath = classpath;
    }

    public void setComment(String commentContent) {
        if (commentContent == null) {
            return;
        }
        String[] comments = commentContent.split("\n");

        Arrays.asList(comments).forEach(comment -> {
            comment = comment.trim();
            if (!comment.isEmpty()) {
                this.comments.add(comment);
            }
        });

        this.comment = String.join("\n", this.comments);
    }

    public String getComment() {
        return comment;
    }

    public String getCommentTitle() {
        return CommentUtil.getTitle(comment);
    }

    public int getCommentLength() {
        if (comment == null) {
            return 0;
        }

        return comment.split("\n").length;
    }

    public boolean hasCommentBody() {
        return getCommentLength() > 1;
    }

    /**
     * Get comment body
     *
     * @return String
     */
    public String getCommentBody() {
        return comment;
    }

    /**
     * Convert lf to br
     *
     * @return String
     */
    public String getBrCommentBody() {
        return StringUtil.transformLfToBr(getCommentBody());
    }

    public Boolean hasAnnotation(Class<?> type) {
        boolean hasAnnotation = annotations.containsKey(type.getCanonicalName());
        if (type.getAnnotation(Extendable.class) != null) {
            hasAnnotation = hasAnnotation || (parent != null && parent.hasAnnotation(type));
        }
        return hasAnnotation;
    }

    public AnnotationStub getAnnotation(Class<?> type) {
        AnnotationStub annotationStub = annotations.get(type.getCanonicalName());
        if (type.getAnnotation(Extendable.class) != null && annotationStub == null && parent != null) {
            annotationStub = parent.getAnnotation(type);
        }
        return annotationStub;
    }

    public Stub getParent() {
        return parent;
    }
}
