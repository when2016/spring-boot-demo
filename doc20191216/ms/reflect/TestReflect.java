package ms.reflect;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class TestReflect {
    public static void main(String[] args) throws Exception {
        ArrayList<Integer> list = new ArrayList<>();
        Method method = list.getClass().getMethod("add", Object.class);
        method.invoke(list, "Java reflection");
        System.out.println(list.get(0));

    }
}
