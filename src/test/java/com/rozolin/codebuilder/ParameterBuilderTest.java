package com.rozolin.codebuilder;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by gustavo on 05/09/16.
 */
public class ParameterBuilderTest {


    @Test
    public void testParameterBuilderClass() {
        ParameterBuild build = new ParameterBuild("test").type(String.class);
        Assert.assertEquals("String test", build.getCode());
    }

    @Test
    public void testParameterBuilderFinal() {
        ParameterBuild build = new ParameterBuild("test").type(String.class).setIsFinal(true);
        Assert.assertEquals("final String test", build.getCode());
    }

    @Test
    public void testParameterBuilderFinalAnnotation() {
        AnnotationBuilder annotation = new AnnotationBuilder("Param");

        ParameterBuild build = new ParameterBuild("test").type(String.class)
                .setIsFinal(true)
                .addAnnotation(annotation);

        Assert.assertEquals("@Param final String test", build.getCode());
    }


}
