package com.rozolin.codebuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gustavo on 05/09/16.
 */
public class ClassBuilder {
    private String className;
    private String packageName;
    private List<AccessModifier>  modifiers;

    public ClassBuilder(String className) {
        this.className = className;
        this.modifiers = new ArrayList<AccessModifier>();
    }

    public ClassBuilder setClassName(String className) {
        this.className = className;
        return this;
    }

    public ClassBuilder setPackageName(String packageName) {
        this.packageName = packageName;
        return this;
    }

    public ClassBuilder addModifiers(AccessModifier... modifiers) {
        for (AccessModifier modifier : modifiers) {
            this.modifiers.add(modifier);
        }
        return this;
    }

    public String getClassName() {
        return className;
    }
}
