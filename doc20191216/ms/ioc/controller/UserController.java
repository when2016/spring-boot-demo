package ms.ioc.controller;

import ms.ioc.annotation.Component;
import ms.ioc.annotation.Inject;
import ms.ioc.bean.User;
import ms.ioc.service.UserService;

@Component
public class UserController {
    @Inject
    private UserService userService;

    public void getUser() {
        User user = userService.getUser();
        System.out.println(user);
    }
}
