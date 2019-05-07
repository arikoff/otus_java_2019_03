package ru.otus.homework;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

public class DIYarrayListProxy {

    static <listClass> List createProxedDIYarrayList() {
        InvocationHandler handler = new DemoInvocationHandler(new DIYarrayList<listClass>());
        return (List) Proxy.newProxyInstance(DIYarrayListProxy.class.getClassLoader(),
                new Class<?>[]{List.class}, handler);
    }

    static <listClass> List createProxedDIYarrayList(int size) {
        InvocationHandler handler = new DemoInvocationHandler(new DIYarrayList<listClass>(size));
        return (List) Proxy.newProxyInstance(DIYarrayListProxy.class.getClassLoader(),
                new Class<?>[]{List.class}, handler);
    }

    static class DemoInvocationHandler implements InvocationHandler {
        private final List myClass;
        private final ArrayList<String> methodsToLog;

        DemoInvocationHandler(List myClass)
        {
            this.myClass = myClass;
            this.methodsToLog = new ArrayList<>();

            Class<?> clazz = myClass.getClass();
            Method[] methods = clazz.getMethods();

            for (Method method : methods)  {
                if (method.isAnnotationPresent(Log.class)) {
                    methodsToLog.add(method.getName());
                }
            }
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

            if (methodsToLog.contains(method.getName())) {

                String logStr = "executed method: " + method.getName() + ", param: ";
                boolean first = true;

                if (!(args == null)) {
                    for (Object arg : args) {
                        if (!(first)) {
                            logStr += ", ";
                        }
                        logStr += arg.toString();
                        first = false;
                    }
                }
                System.out.println(logStr);
            }
            return method.invoke(myClass, args);

        }
    }
}
