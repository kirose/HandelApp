package com.handel.util;

/**
 * Created by Marco Antonio on 22/03/2018.
 */

public final class UtilString {
    public static boolean isBlank(String s){
        return s == null || "".equals(s.trim());
    }
    public static String capitalize(String val){
        if (isBlank(val)){
            return val;
        }
        StringBuilder s = new StringBuilder();
        int ln = val.length();
        char c;
        for(int i = 0; i < ln; i++){
            c = val.charAt(i);
            if (c == ' '){
                i++;
                // Hola  mama -> HolaMama
                while(i < ln && (c = s.charAt(i)) == ' '){i++;}
                // Hola      -> Hola
                if (c == ' '){
                    break;
                }
                c = Character.toUpperCase(c);
            }
            s.append(c);
        }
        s.replace(0,1,""+Character.toUpperCase(s.charAt(0)));
        return s.toString();
    }
    public static String unserscoreToCamelcase(String name){
        if (name == null){
            return null;
        }
        StringBuilder s = new StringBuilder();
        int ln = name.length();
        char c;
        for(int i = 0; i < ln; i++){
            c = name.charAt(i);
            if (c == '_'){
                i++;
                // Hola__mama -> HolaMama
                while(i < ln && (c = name.charAt(i)) == '_'){i++;}
                // Hola____ -> Hola
                if (c == '_'){
                    break;
                }
                c = Character.toUpperCase(c);
            }
            s.append(c);
        }
        return s.toString();
    }
    public static String camelcaseToUnserscore(String name){
        if (name == null){
            return null;
        }
        return name.replaceAll("([a-z])([A-Z]+)", "$1_$2").toLowerCase();
    }
}
