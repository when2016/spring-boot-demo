package ms.ioc.service;

import ms.ioc.annotation.Component;
import ms.ioc.bean.User;

/**
 * 用户Service实现
 */
@Component
public class UserService {

    public User getUser() {
        User user = new User("王红恩", 36);
        return user;
    }

}
