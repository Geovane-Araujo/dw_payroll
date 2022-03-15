package br.com.populateNames.util;

import java.lang.reflect.InvocationTargetException;

public class Util {

    public static <T> T bean(Class clazz)  {
        try {
            return (T)clazz.getConstructor().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }
}
