package com.rozolin.codebuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gustavo on 20/09/16.
 */
public class ParameterBuild {

    private String parameterName;
    private Class<?> parameterType;
    private ClassBuilder parameterTypeClassBuilder;
    private List<AnnotationBuilder> annotationBuilders = new ArrayList<AnnotationBuilder>();;
    private boolean isFinal = false;

    public ParameterBuild(String parameterName) {
        this.parameterType = parameterType;
        this.parameterName = parameterName;
    }

    public ParameterBuild type(Class<?> type){
        this.parameterType = type;
        return this;
    }

    public ParameterBuild type(ClassBuilder type){
        this.parameterTypeClassBuilder = type;
        return this;
    }

    public ParameterBuild setIsFinal(boolean isFinal){
        this.isFinal = isFinal;
        return this;
    }

    public ParameterBuild addAnnotation(AnnotationBuilder annotationBuilder){
        this.annotationBuilders.add(annotationBuilder);
        return this;
    }

    public String getCode(){
        StringBuilder code = new StringBuilder();
        for (AnnotationBuilder builder : annotationBuilders) {
            code.append(builder.getCode()+" ");
        }
        if(this.isFinal){
            code.append("final ");
        }
        if(this.parameterType != null){
            code.append(this.parameterType.getSimpleName() + " ");
        }else {
            code.append(parameterTypeClassBuilder.getClassName() + " ");
        }
        code.append(this.parameterName);
        return code.toString();
    }

}
