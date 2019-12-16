package ms.aop;

public class HelloImpl implements IHello {
    @Override
    public void sayHello() {
        System.out.println("Hello World....");
    }
}
