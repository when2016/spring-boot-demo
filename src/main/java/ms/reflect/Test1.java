package ms.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Test1 {


    public static void main(String[] args) throws Exception {
        Class c = Student.class;
        Constructor[] cs = c.getConstructors();
        for (Constructor constructor : cs) {
            Student st = (Student) constructor.newInstance();
            System.out.println(st);
        }

        System.out.println("-----------------");
        Field[] fs = c.getDeclaredFields();
        for (Field field : fs) {
            System.out.println(field);
        }

        System.out.println("-----------------");
        Method[] ms = c.getMethods();
        //Method[] ms = c.getDeclaredMethods();
        for (Method method : ms) {
            System.out.println(method);
        }

        System.out.println("===============");
        final byte bt1 = 1;
        final byte bt2 = 2;
        byte bt3 = bt1 + bt2;
        System.out.println(bt1);
        System.out.println(bt2);
        System.out.println(bt3);

    }
}
