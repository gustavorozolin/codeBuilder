package com.rozolin.codebuilder;

/**
 * Created by gustavo on 05/09/16.
 */
public enum AccessModifier {

    //Access Control Modifiers
    DEFAULT(""),
    PRIVATE("private"),
    PUBLIC("public"),
    PROTECTED("protected");



    private String modifierName;

    AccessModifier(String modifierName) {
        this.modifierName = modifierName;
    }

    public String getModifierName() {
        return modifierName;
    }
}
