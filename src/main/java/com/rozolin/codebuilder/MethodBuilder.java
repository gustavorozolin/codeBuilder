package com.rozolin.codebuilder;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by gustavo on 20/09/16.
 */
public class MethodBuilder {
    private String name;
    private Class<?> returnType;
    private ClassBuilder returnTypeClassBuilder;
    private List<ParameterBuild> parameterBuilds = new ArrayList<ParameterBuild>();
    private Set<AccessModifier> modifiers = new LinkedHashSet<AccessModifier>();
    private List<AnnotationBuilder> annotationBuilders = new ArrayList<AnnotationBuilder>();
    private List<String> statements = new ArrayList<String>();
}
