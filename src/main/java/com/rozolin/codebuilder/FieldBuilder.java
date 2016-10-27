package com.rozolin.codebuilder;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by gustavo on 19/09/16.
 */
public class FieldBuilder {

    private String fieldName;
    private Set<AccessModifier> modifiers = new LinkedHashSet<AccessModifier>();
    private Class<?> typeClass;
    private ClassBuilder typeClassBuilder;
    private List<AnnotationBuilder> annotationBuilders = new ArrayList<AnnotationBuilder>();
    private boolean isFinal = false;
    private boolean isStatic = false;
    private AccessModifier accessModifier = AccessModifier.DEFAULT;

    public FieldBuilder(String fieldName) {
        this.fieldName = fieldName;
    }

    public FieldBuilder accessModifier(AccessModifier accessModifier){
        this.accessModifier = accessModifier;
        return this;
    }

    public FieldBuilder type(Class<?> typeClass){
        this.typeClass = typeClass;
        return this;
    }

    public FieldBuilder type(ClassBuilder typeClassBuilder){
        this.typeClassBuilder = typeClassBuilder;
        return this;
    }

    public FieldBuilder setIsStatic(boolean isStatic){
        this.isStatic = isStatic;
        return this;
    }

    public FieldBuilder setIsFinal(boolean isFinal){
        this.isFinal = isFinal;
        return this;
    }

    public FieldBuilder addAnnotation(AnnotationBuilder annotation){
        this.annotationBuilders.add(annotation);
        return this;
    }


    public String getCode(){
        StringBuilder code = new StringBuilder();
        for (AnnotationBuilder builder : annotationBuilders) {
            code.append(builder.getCode()+"\n");
        }

        if(this.accessModifier != AccessModifier.DEFAULT){
            code.append(this.accessModifier.getModifierName()+" ");
        }

        if(isStatic){
            code.append("static ");
        }
        if(isFinal){
            code.append("final ");
        }

        if(this.typeClass != null){
            code.append(this.typeClass.getSimpleName() + " ");
        }else {
            code.append(typeClassBuilder.getClassName() + " ");
        }

        code.append(this.fieldName + ";");
        return code.toString();
    }
}
