package com.handel.util;

import android.util.Log;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Marco Antonio on 22/03/2018.
 */

public final class UtilReflect {
    public static <T>Object invoke(T instance, String name, Object ... args){
        if (args == null){
            try {
                return instance.getClass().getMethod(name).invoke(instance);
            } catch (IllegalAccessException e) {
            } catch (InvocationTargetException e) {
            } catch (NoSuchMethodException e) {
            }
            return null;
        }
        Class<?>[] types = new Class<?>[args.length];
        for (int i = 0; i < args.length; i++){
            types[i] = args[i].getClass();
        }
        try {
            return instance.getClass().getMethod(name,types).invoke(instance,args);
        } catch (IllegalAccessException e) {
        } catch (InvocationTargetException e) {
        } catch (NoSuchMethodException e) {
        }
        return null;
    }
    public static Method getMethod(Class<?> clazz, String name, Class<?> ... types){
        try {
            return clazz.getMethod(name, types);
        } catch (NoSuchMethodException e) {
        }
        return null;
    }
    public static <T extends Annotation> Field getFieldWithAnnotation(Class<?> classObject, Class<T> classAnnotation){
        Field[] fields = classObject.getFields();
        for (Field f : fields) {
            if (f.getAnnotation(classAnnotation) != null) {
                return f;
            }
        }
        return null;
    }
}
