package ms.aop;

import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;

//https://www.jianshu.com/p/e63b0685e8ee
public class Main {
    public static void main(String[] args) {
        ProxyHandler proxy = new ProxyHandler(new HelloImpl());
        IHello hello = (IHello) proxy.proxyInstance();
        hello.sayHello();


        ///
        String name = "ProxyHello";
        byte[] data = ProxyGenerator.generateProxyClass(name, new Class[]{IHello.class});
        try {
            FileOutputStream out = new FileOutputStream("d:\\" + name + ".class");
            out.write(data);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
