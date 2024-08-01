package augustxun.rpc.consumer;

import augustxun.rpc.bootstrap.ConsumerBootstrap;
import augustxun.rpc.common.model.User;
import augustxun.rpc.common.service.UserService;
import augustxun.rpc.config.RpcConfig;
import augustxun.rpc.proxy.ServiceProxyFactory;
import augustxun.rpc.utils.ConfigUtils;

public class ConsumerExample {
    public static void main(String[] args) {
        // 服务提供者初始化
        ConsumerBootstrap.init();
        // 获取代理
        UserService userService = ServiceProxyFactory.getProxy(UserService.class);
        User user = new User();
        user.setName("augustxun");
        // 调用
        User newUser = userService.getUser(user);
        if (newUser != null) {
            System.out.println(newUser.getName());
        } else {
            System.out.println("user == null");
        }
    }
}
