package ms.ioc;

import ms.ioc.controller.UserController;

/**
 * 模拟调用UserController
 */
public class Main {
    public static void main(String[] args) throws Exception {
        UserController userController = (UserController) IocContext.applicationContext.get(UserController.class);
        userController.getUser();
    }
}
