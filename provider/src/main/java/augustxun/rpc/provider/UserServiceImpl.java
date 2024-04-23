package augustxun.rpc.provider;

import augustxun.rpc.common.service.UserService;
import augustxun.rpc.common.model.User;

public class UserServiceImpl implements UserService {


    @Override
    public User getUser(User user) {
        System.out.println("用户名:" + user.getName());
        return user;
    }
}
