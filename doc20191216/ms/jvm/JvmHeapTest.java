package ms.jvm;

import java.util.ArrayList;
import java.util.List;

//https://blog.csdn.net/qq_31615049/article/details/82980799
public class JvmHeapTest {
    public static void main(String[] args) {
        List<String> aList = new ArrayList<>();

        try {
            while (true) {
                aList.add("abcd");
            }
        } catch (Throwable e) {
            System.out.println(aList.size());
            e.printStackTrace();
        }
    }


}
