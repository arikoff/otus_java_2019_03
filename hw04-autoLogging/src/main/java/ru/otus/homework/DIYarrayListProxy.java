package ru.otus.homework;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DIYarrayListProxy {

    static <T> List createProxedDIYarrayList() {
        InvocationHandler handler = new DemoInvocationHandler(new DIYarrayList());
        return (List) Proxy.newProxyInstance(DIYarrayListProxy.class.getClassLoader(),
                new Class<?>[]{List.class}, handler);
    }

    static <T> List createProxedDIYarrayList(int size) {
        InvocationHandler handler = new DemoInvocationHandler(new DIYarrayList<T>(size));
        return (List) Proxy.newProxyInstance(DIYarrayListProxy.class.getClassLoader(),
                new Class<?>[]{List.class}, handler);
    }

    static class DemoInvocationHandler implements InvocationHandler {

        private final Object myClass;
        private final List<String> methodsToLog;

        DemoInvocationHandler(Object myClass)
        {
            this.myClass = myClass;
            this.methodsToLog = new ArrayList<>();

            Class<?> clazz = myClass.getClass();
            Method[] methods = clazz.getMethods();

            for (Method method : methods)  {
                if (method.isAnnotationPresent(Log.class)) {
                    methodsToLog.add(getMethodNameWithParameterTypes(method));
                }
            }
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

            for (String methodToLog : methodsToLog) {
                if (getMethodNameWithParameterTypes(method).equals(methodToLog)) {
                    System.out.println(getLogMessage(method.getName(), args));
                    break;
                }
            }
            return method.invoke(myClass, args);
        }

        private String getMethodNameWithParameterTypes(Method method){
            return method.getName() + "("
                    + Arrays.stream(method.getParameterTypes()).map(Class::toString).collect(Collectors.joining(","))
                    + ")";
        }

        public String getLogMessage(String methodName, Object[] args) {

            String logStr = "executed method: " + methodName + ", param: ";
            String strArgs = "";

            if (args != null) {
                strArgs = Arrays.stream(args)
                        .map(Object::toString)
                        .collect(Collectors.joining(", "));
            }
            return logStr + strArgs;
        }
    }
}
