package com.rozolin.codebuilder;

import org.junit.Assert;
import org.junit.Test;

import java.lang.annotation.Target;
import java.util.Date;

/**
 * Created by gustavo on 05/09/16.
 */
public class AnnotationBuilderTest {


    @Test
    public void testAnnotationBuilderClass() {
        AnnotationBuilder annotationBuilder = new AnnotationBuilder(Target.class);
        Assert.assertEquals("@Target", annotationBuilder.getCode());
    }
    @Test
    public void testAnnotationBuilderClassString() {
        AnnotationBuilder namedQueries = new AnnotationBuilder("javax.persistence.NamedQueries");
    //    System.out.println(namedQueries.getCode());
        Assert.assertEquals("@NamedQueries", namedQueries.getCode());

        AnnotationBuilder namedQuery = new AnnotationBuilder("javax.persistence.NamedQuery")
                .addMember("name","findAllCustomersWithName")
                .addMember("query","SELECT c FROM Customer c WHERE c.name LIKE :custName");

        AnnotationBuilder namedQuery2 = new AnnotationBuilder("javax.persistence.NamedQuery")
                .addMember("name","findAllCustomersWithLastName")
                .addMember("query","SELECT c FROM Customer c WHERE c.name LIKE :lastName");
        //      System.out.println(namedQuery.getCode());
        Assert.assertEquals("@NamedQuery( name=\"findAllCustomersWithName\", query=\"SELECT c FROM Customer c WHERE c.name LIKE :custName\" )", namedQuery.getCode());

        namedQueries.addMember("value",new AnnotationBuilder[]{namedQuery,namedQuery2});
//        System.out.println(namedQueries.getCode());
        Assert.assertEquals("@NamedQueries( value={ @NamedQuery( name=\"findAllCustomersWithName\", query=\"SELECT c FROM Customer c WHERE c.name LIKE :custName\" ), @NamedQuery( name=\"findAllCustomersWithLastName\", query=\"SELECT c FROM Customer c WHERE c.name LIKE :lastName\" ) } )", namedQueries.getCode());

    }

    @Test
    public void testAnnotationBuilderByte() {
        AnnotationBuilder annotationBuilder = new AnnotationBuilder("com.rozolin.Annotation")
                .addMember("value",Byte.MAX_VALUE)
                .addMember("values",new byte[]{1,2});
        Assert.assertEquals("@Annotation( value=127, values={ 1, 2 } )", annotationBuilder.getCode());
    }

    @Test
    public void testAnnotationBuilderShort() {
        AnnotationBuilder annotationBuilder = new AnnotationBuilder("com.rozolin.Annotation")
                .addMember("value",Short.MAX_VALUE)
                .addMember("values",new short[]{1,2});
        Assert.assertEquals("@Annotation( value=32767, values={ 1, 2 } )", annotationBuilder.getCode());
    }

    @Test
    public void testAnnotationBuilderInt() {
        AnnotationBuilder annotationBuilder = new AnnotationBuilder("com.rozolin.Annotation")
                .addMember("value",Integer.MAX_VALUE)
                .addMember("values",new int[]{1,2});
        Assert.assertEquals("@Annotation( value=2147483647, values={ 1, 2 } )", annotationBuilder.getCode());
    }

    @Test
    public void testAnnotationBuilderLong() {
        AnnotationBuilder annotationBuilder = new AnnotationBuilder("com.rozolin.Annotation")
                .addMember("value",Long.MAX_VALUE)
                .addMember("values",new long[]{1L,2L});
        Assert.assertEquals("@Annotation( value=9223372036854775807L, values={ 1L, 2L } )", annotationBuilder.getCode());
    }

    @Test
    public void testAnnotationBuilderFloat() {
        AnnotationBuilder annotationBuilder = new AnnotationBuilder("com.rozolin.Annotation")
                .addMember("value",Float.MAX_VALUE)
                .addMember("values",new float[]{1F,2F});
        Assert.assertEquals("@Annotation( value=3.4028235E38F, values={ 1.0F, 2.0F } )", annotationBuilder.getCode());
    }

    @Test
    public void testAnnotationBuilderDouble() {
        AnnotationBuilder annotationBuilder = new AnnotationBuilder("com.rozolin.Annotation")
                .addMember("value",Double.MAX_VALUE)
                .addMember("values",new double[]{1D,2D});
        Assert.assertEquals("@Annotation( value=1.7976931348623157E308D, values={ 1.0D, 2.0D } )", annotationBuilder.getCode());
    }

    @Test
    public void testAnnotationBuilderBoolean (){
        AnnotationBuilder annotationBuilder = new AnnotationBuilder("com.rozolin.Annotation")
                .addMember("value",false)
                .addMember("values",new boolean[]{true,false});
        Assert.assertEquals("@Annotation( value=false, values={ true, false } )", annotationBuilder.getCode());
    }

    @Test
    public void testAnnotationBuilderString (){
        AnnotationBuilder annotationBuilder = new AnnotationBuilder("com.rozolin.Annotation")
                .addMember("value","String")
                .addMember("values",new String[]{"String1","String2"});
        Assert.assertEquals("@Annotation( value=\"String\", values={ \"String1\", \"String2\" } )", annotationBuilder.getCode());
    }

    @Test
    public void testAnnotationBuilderClassMembers (){
        AnnotationBuilder annotationBuilder = new AnnotationBuilder("com.rozolin.Annotation")
                .addMember("value",String.class)
                .addMember("values",new Class[]{String.class, Date.class});
        Assert.assertEquals("@Annotation( value=String.class, values={ String.class, Date.class } )", annotationBuilder.getCode());
    }

    @Test
    public void testAnnotationBuilderEnum (){
        AnnotationBuilder annotationBuilder = new AnnotationBuilder("com.rozolin.Annotation")
                .addMember("value", AccessModifier.PRIVATE)
                .addMember("values",new AccessModifier[]{AccessModifier.DEFAULT,AccessModifier.PROTECTED});
        Assert.assertEquals("@Annotation( value=PRIVATE, values={ DEFAULT, PROTECTED } )", annotationBuilder.getCode());
    }

    @Test
    public void testAnnotationBuilderAnnotation (){
        AnnotationBuilder annotationBuilder = new AnnotationBuilder("com.rozolin.Annotation")
                .addMember("value", new AnnotationBuilder("com.rozolin.Annotation1"))
                .addMember("values",new AnnotationBuilder[]{new AnnotationBuilder("com.rozolin.Annotation2"),new AnnotationBuilder("com.rozolin.Annotation3")});
        Assert.assertEquals("@Annotation( value=@Annotation1, values={ @Annotation2, @Annotation3 } )", annotationBuilder.getCode());
    }

    @Test
    public void testAnnotationBuilderChar (){
        AnnotationBuilder annotationBuilder = new AnnotationBuilder("com.rozolin.Annotation")
                .addMember("value", 'a')
                .addMember("values",new char[]{'a','b'});
        Assert.assertEquals("@Annotation( value='a', values={ 'a', 'b' } )", annotationBuilder.getCode());
    }
}
