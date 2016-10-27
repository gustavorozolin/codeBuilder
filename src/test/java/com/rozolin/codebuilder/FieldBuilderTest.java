package com.rozolin.codebuilder;

import org.junit.Assert;
import org.junit.Test;

import java.lang.annotation.Target;
import java.util.Date;

/**
 * Created by gustavo on 05/09/16.
 */
public class FieldBuilderTest {


    @Test
    public void testFieldBuilderClass() {
        FieldBuilder fieldBuilder = new FieldBuilder("test").type(String.class);
        Assert.assertEquals("String test;", fieldBuilder.getCode());
    }

    @Test
    public void testFieldBuilderClassModifier1() {
        FieldBuilder fieldBuilder = new FieldBuilder("test")
                .accessModifier(AccessModifier.PRIVATE)
                .type(String.class);
        Assert.assertEquals("private String test;", fieldBuilder.getCode());
    }

    @Test
    public void testFieldBuilderClassModifier2() {
        FieldBuilder fieldBuilder = new FieldBuilder("TEST")
                .type(String.class).setIsStatic(true).accessModifier(AccessModifier.PRIVATE);
        Assert.assertEquals("private static String TEST;", fieldBuilder.getCode());
    }

    @Test
    public void testFieldBuilderClassModifier3() {
        FieldBuilder fieldBuilder = new FieldBuilder("TEST")
                .type(String.class)
                .setIsStatic(true)
                .setIsFinal(true)
                .accessModifier(AccessModifier.PRIVATE);
        Assert.assertEquals("private static final String TEST;", fieldBuilder.getCode());
    }

    @Test
    public void testFieldBuilderClassAnnotation() {
        AnnotationBuilder column = new AnnotationBuilder("javax.persistence.Column");
        FieldBuilder fieldBuilder = new FieldBuilder("name")
                .type(String.class)
                .accessModifier(AccessModifier.PRIVATE);
        fieldBuilder.addAnnotation(column);
        Assert.assertEquals("@Column\n" + "private String name;", fieldBuilder.getCode());
    }

}
