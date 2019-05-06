package ru.otus.homework;

import lombok.SneakyThrows;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class AnnotationsTestLauncher {

    private Object instance;

    @SneakyThrows
    public static void launch(String className) {

        Class<?> clazz = Class.forName(className);
        Constructor<?> constructor = clazz.getConstructor(int.class);

        Method[] methods = clazz.getMethods();

        DIYarrayList<Method> beforeMethods = new DIYarrayList<>();
        DIYarrayList<Method> testMethods = new DIYarrayList<>();
        DIYarrayList<Method> afterMethods = new DIYarrayList<>();

        for (Method method : methods)  {
            if (method.isAnnotationPresent(Before.class)) {
                beforeMethods.add(method);
            }
            if (method.isAnnotationPresent(After.class)) {
                afterMethods.add(method);
            }
            if (method.isAnnotationPresent(Test.class)) {
                testMethods.add(method);
            }
        }

        int num = 0;

        for (Method method : testMethods)  {
            if (method.isAnnotationPresent(Test.class)) {
                System.out.println("-----------------------------------------------" + Integer.toString(num));
                boolean hasException = false;
                Object instance = constructor.newInstance(num);
                for (int i = 0; i < beforeMethods.size(); i++) {
                    try {
                        beforeMethods.get(i).invoke(instance);
                        System.out.println("Before " + method.getName() + " - ok");
                    }
                    catch (Exception e) {
                        hasException = true;
                        System.out.println("Before " + method.getName() + " - exception: " + e.getMessage());
                    }
                }
                if (!(hasException)) {
                    method.invoke(instance);
                }
                for (int i = 0; i < afterMethods.size(); i++) {
                    afterMethods.get(i).invoke(instance);
                }
                num++;
            }
        }

    }

}
