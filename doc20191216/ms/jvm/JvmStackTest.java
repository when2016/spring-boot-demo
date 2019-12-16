package ms.jvm;

public class JvmStackTest {
    private int i = 0;

    public void a() {
        System.out.println(i++);
        a();
    }

    public static void main(String[] args) {
        JvmStackTest j = new JvmStackTest();
        j.a();
    }
}
