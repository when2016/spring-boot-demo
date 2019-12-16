package ms.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyHandler implements InvocationHandler {

    private Object target;

    public ProxyHandler(Object target) {
        this.target = target;
    }

    public Object proxyInstance() {
        System.out.println(target.getClass().getClassLoader().toString());
        System.out.println(target.getClass().getInterfaces().length);
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("aspect before...");
        Object result = method.invoke(this.target, args);
        System.out.println("aspect after...");

        return result;
    }
}
