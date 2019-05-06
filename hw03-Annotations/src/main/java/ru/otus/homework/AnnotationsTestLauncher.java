package ru.otus.homework;

import lombok.SneakyThrows;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class AnnotationsTestLauncher {

    @SneakyThrows
    public static void Launch(String className) {

        Class<?> clazz = Class.forName(className);
        Constructor<?> constructor = clazz.getConstructor();
        Object instance = constructor.newInstance();

        Method[] methods = clazz.getMethods();

        for (Method method : methods)  {
            if (method.isAnnotationPresent(Before.class)) {
                    method.invoke(instance);
            }
        }

        for (Method method : methods)  {
            if (method.isAnnotationPresent(Test.class)) {
                    method.invoke(instance);
            }
        }

        for (Method method : methods)  {
            if (method.isAnnotationPresent(After.class)) {
                    method.invoke(instance);
            }
        }

    }

}
