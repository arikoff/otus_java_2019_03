package ru.otus.homework;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

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

        private final List myClass;
        private final ArrayList<Method> methodsToLog;

        DemoInvocationHandler(List myClass)
        {
            this.myClass = myClass;
            this.methodsToLog = new ArrayList<>();

            Class<?> clazz = myClass.getClass();
            Method[] methods = clazz.getMethods();

            for (Method method : methods)  {
                if (method.isAnnotationPresent(Log.class)) {
                    methodsToLog.add(method);
                }
            }

        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

            for (Method methodToLog : methodsToLog) {
                if (isMethodsEqual(method, methodToLog)) {
                    System.out.println(getLogMessage(method.getName(), args));
                    break;
                }
            }

            return method.invoke(myClass, args);

        }

        private boolean isMethodsEqual(Method obj1, Method obj2) {
            if (obj1 != null && obj2 != null) {
                if (obj1.getName() == obj2.getName()) {
                    if (!obj1.getReturnType().equals(obj2.getReturnType()))
                        return false;
                    return equalParamTypes(obj1.getParameterTypes(), obj2.getParameterTypes());
                }
            }
            return false;
        }

        private boolean equalParamTypes(Class<?>[] params1, Class<?>[] params2) {
            /* Avoid unnecessary cloning */
            if (params1.length == params2.length) {
                for (int i = 0; i < params1.length; i++) {
                    if (params1[i] != params2[i])
                        return false;
                }
                return true;
            }
            return false;
        }

        public String getLogMessage(String methodName, Object[] args) {

            String logStr = "executed method: " + methodName + ", param: ";
            boolean first = true;

            if (args != null) {
                for (Object arg : args) {
                    if (!(first)) {
                        logStr += ", ";
                    }
                    logStr += arg.toString();
                    first = false;
                }
            }

            return logStr;

        }
    }
}
