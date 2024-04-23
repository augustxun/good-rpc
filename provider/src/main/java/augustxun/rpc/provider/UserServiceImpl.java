package augustxun.rpc.provider;

import augustxun.rpc.common.service.UserService;
import augustxun.rpc.common.model.User;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserServiceImpl implements UserService {


    @Override
    public User getUser(User user) {
        log.info("用户名:" + user.getName());
        return user;
    }
}
