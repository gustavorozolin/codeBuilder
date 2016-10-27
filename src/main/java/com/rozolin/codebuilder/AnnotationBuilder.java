package com.rozolin.codebuilder;

import sun.plugin.navig.motif.OJIPlugin;

import java.lang.annotation.Annotation;
import java.util.*;

/**
 * Created by gustavo on 05/09/16.
 */
public class AnnotationBuilder {
    public Class<?> annotation;
    public String annotationName;

    public Map<String, Object> members = new LinkedHashMap<String, Object>();

    public AnnotationBuilder(Class<? extends Annotation> annotation) {
        this.annotation = annotation;
    }

    public AnnotationBuilder(String annotationName) {
        this.annotationName = annotationName;
    }

    public AnnotationBuilder addMember(String name, byte value){

        return addMemberAll(name,""+value);
    }
    public AnnotationBuilder addMember(String name, byte[] value){
        StringBuilder sb = new StringBuilder();
        for (byte pri : value) {
            sb.append(" "+pri+",");
        }
        return addMemberAll(name, "{"+sb.substring(0,sb.length()-1)+" }");
    }

    public AnnotationBuilder addMember(String name, short value){
        return addMemberAll(name,value);
    }
    public AnnotationBuilder addMember(String name, short[] value){
        return addMemberAll(name, value);
    }

    public AnnotationBuilder addMember(String name, int value){
        return addMemberAll(name,value);
    }
    public AnnotationBuilder addMember(String name, int[] value){
        return addMemberAll(name, value);
    }

    public AnnotationBuilder addMember(String name, long value){
        return addMemberAll(name,value);
    }
    public AnnotationBuilder addMember(String name, long[] value){
        return addMemberAll(name, value);
    }

    public AnnotationBuilder addMember(String name, float value){
        return addMemberAll(name,value);
    }
    public AnnotationBuilder addMember(String name, float[] value){
        return addMemberAll(name, value);
    }

    public AnnotationBuilder addMember(String name, double value){
        return addMemberAll(name,value);
    }
    public AnnotationBuilder addMember(String name, double[] value){
        return addMemberAll(name, value);
    }

    public AnnotationBuilder addMember(String name, boolean value){
        return addMemberAll(name,value);
    }

    public AnnotationBuilder addMember(String name, boolean[] value){
        return addMemberAll(name, value);
    }

    public AnnotationBuilder addMember(String name, char value){
        return addMemberAll(name,value);
    }
    public AnnotationBuilder addMember(String name, char[] value){
        return addMemberAll(name, value);
    }

    public AnnotationBuilder addMember(String name, String value){
        return addMemberAll(name,value);
    }
    public AnnotationBuilder addMember(String name, String[] value){
        return addMemberAll(name, value);
    }

    public AnnotationBuilder addMember(String name, Class<?> value){
        return addMemberAll(name,value);
    }

    public AnnotationBuilder addMember(String name, Class<?>[] value){
        return addMemberAll(name, value);
    }

    public AnnotationBuilder addMember(String name, Enum value){
        return addMemberAll(name,value);
    }

    public AnnotationBuilder addMember(String name, Enum[] value){
        return addMemberAll(name, value);
    }

    public AnnotationBuilder addMember(String name, AnnotationBuilder value){
        return addMemberAll(name,value);
    }
    public AnnotationBuilder addMember(String name, AnnotationBuilder[] value){
        return addMemberAll(name, value);
    }

    private AnnotationBuilder addMemberAll(String name, Object value){
        this.members.put(name,value);
        return this;
    }

    public String getCode(){
        StringBuilder code = new StringBuilder("@");
        if(this.annotation != null){
            code.append(this.annotation.getSimpleName());
        }else if(this.annotationName != null){
            String[] ss = this.annotationName.split("\\.");
            final String className = ss[ss.length - 1];
            code.append(className);
        }
        if (!this.members.isEmpty()){
            StringBuilder sb = new StringBuilder();
            final Set<String> keySet = members.keySet();
            for (String key : keySet) {
                final Object value = members.get(key);
                String valueString = getStringValue(value);
                sb.append(" "+key+"="+valueString+",");
            }

            code.append("("+sb.substring(0,sb.length()-1)+" )");
        }
        return code.toString();
    }

    private String getStringValue(Object value) {

        if(value instanceof Byte || value instanceof Short
                || value instanceof Integer
                || value instanceof Boolean
                ){
            return ""+value;
        }else if(value instanceof Long){
            return value+"L";
        }else if(value instanceof Float){
            return value+"F";
        }else if(value instanceof Double){
            return value+"D";
        }else if(value instanceof byte[]){
            StringBuilder sb = new StringBuilder();
            for (byte pri : (byte[])value) {
                sb.append(" "+pri+",");
            }
            return  "{"+sb.substring(0,sb.length()-1)+" }";
        }else if (value instanceof short[]){
            StringBuilder sb = new StringBuilder();
            for (short pri : (short[]) value) {
                sb.append(" "+pri+",");
            }
            return "{"+sb.substring(0,sb.length()-1)+" }";
        }else if (value instanceof int[]){
            StringBuilder sb = new StringBuilder();
            for (int pri : (int[]) value) {
                sb.append(" "+pri+",");
            }
            return "{"+sb.substring(0,sb.length()-1)+" }";
        }else if (value instanceof long[]){
            StringBuilder sb = new StringBuilder();
            for (long pri : (long[]) value) {
                sb.append(" "+pri+"L,");
            }
            return "{"+sb.substring(0,sb.length()-1)+" }";
        }else if (value instanceof float[]){
            StringBuilder sb = new StringBuilder();
            for (float pri : (float[]) value) {
                sb.append(" "+pri+"F,");
            }
            return "{"+sb.substring(0,sb.length()-1)+" }";
        }else if(value instanceof double[]){
            StringBuilder sb = new StringBuilder();
            for (double pri : (double[]) value) {
                sb.append(" "+pri+"D,");
            }
            return "{"+sb.substring(0,sb.length()-1)+" }";
        }else if(value instanceof boolean[]){
            StringBuilder sb = new StringBuilder();
            for (boolean pri : (boolean[]) value) {
                sb.append(" "+pri+",");
            }
            return "{"+sb.substring(0,sb.length()-1)+" }";
        }else if(value instanceof Character){
            return "'"+value+"'";
        }else if(value instanceof char[]){
            StringBuilder sb = new StringBuilder();
            for (char pri : (char[]) value) {
                sb.append(" '"+pri+"',");
            }
            return "{"+sb.substring(0,sb.length()-1)+" }";
        }else if(value instanceof String[]){
            StringBuilder sb = new StringBuilder();
            for (String pri : (String[]) value) {
                sb.append(" \""+pri+"\",");
            }
            return "{"+sb.substring(0,sb.length()-1)+" }";
        }else if (value instanceof String){
            return "\""+value+"\"";
        }else if(value instanceof Class){
            return ((Class)value).getSimpleName()+".class";
        }else if (value instanceof Class<?>[]){
            StringBuilder sb = new StringBuilder();
            for (Class pri : (Class<?>[])value) {
                sb.append(" "+pri.getSimpleName()+".class,");
            }
            return "{"+sb.substring(0,sb.length()-1)+" }";
        }else if(value instanceof Enum){
            return ((Enum)value).name();
        }else if (value instanceof Enum[]){
            StringBuilder sb = new StringBuilder();
            for (Enum pri : (Enum[])value) {
                sb.append(" "+pri.name()+",");
            }
            return "{"+sb.substring(0,sb.length()-1)+" }";
        }else if(value instanceof AnnotationBuilder){
            return ((AnnotationBuilder)value).getCode();
        }else if (value instanceof AnnotationBuilder[]){
            StringBuilder sb = new StringBuilder();
            for (AnnotationBuilder pri : (AnnotationBuilder[])value) {
                sb.append(" "+pri.getCode()+",");
            }
            return "{"+sb.substring(0,sb.length()-1)+" }";
        }

        return null;
    }
}
