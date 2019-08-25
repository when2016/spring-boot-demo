package ms.program;

import com.sun.org.apache.xpath.internal.operations.String;

public class Test {
    public static void main(String[] args) {
        System.out.println(get100_0(100));
        long k = 1;
        for (int i = 100; i >= 1; i--) {
            k = k * i;
        }
        System.out.println(k);


    }

    public static int get100_0(int in) {
        int count = 0;
        for (int i = 5; i <= in; i++) {
            if (i % 5 == 0) {
                count++;
                if (i % (5 * 5) == 0) {
                    count++;
                }
            }
        }
        return count;
    }
}
