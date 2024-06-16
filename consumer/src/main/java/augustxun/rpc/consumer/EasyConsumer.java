package augustxun.rpc.consumer;

import augustxun.rpc.common.model.User;
import augustxun.rpc.common.service.UserService;
import augustxun.rpc.config.RpcConfig;
import augustxun.rpc.proxy.ServiceProxyFactory;
import augustxun.rpc.utils.ConfigUtils;

public class EasyConsumer {
    public static void main(String[] args) {
        RpcConfig rpc = ConfigUtils.loadConfig(RpcConfig.class, "rpc");
        System.out.println(rpc);
        User user = new User();
        user.setName("augustxun");
        UserService userService = ServiceProxyFactory.getProxy(UserService.class);
        User newUser = userService.getUser(user);
        if (newUser != null) {
            System.out.println(newUser.getName());
        } else {
            System.out.println(user == null);
        }
    }
}
