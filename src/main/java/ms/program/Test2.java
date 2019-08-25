package ms.program;

public class Test2 {
    public static void main(String[] args) {
        abc();
        byte[] bs = {(byte) 0xff, 0x0F, 0x1F, 0x2F, 0x3F, 0x4F, 0x5F, 0x6F};
        for (int i = 0; i < bs.length; i++) {
            int tempI = (int) (bs[i] & 0xff);
            String temp16 = Integer.toHexString(tempI);
            System.out.println("tan," +"i=" + i + ", 10=" + tempI + ", 16=" + temp16);
        }
    }

    public static void abc() {
        try {
            return;
        } catch (Exception e) {
            System.out.println("aaa");
        } finally {
            System.out.println("dd");
        }
    }
}
