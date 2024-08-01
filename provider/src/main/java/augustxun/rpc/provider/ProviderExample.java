package augustxun.rpc.provider;

import augustxun.rpc.bootstrap.ProviderBootstrap;
import augustxun.rpc.common.service.UserService;
import augustxun.rpc.model.ServiceRegisterInfo;
import java.util.ArrayList;
import java.util.List;

/**
 * 服务提供者示例
 */
public class ProviderExample {
    public static void main(String[] args) {
        // 要注册的服务
        List<ServiceRegisterInfo> serviceRegisterInfoList = new ArrayList<>();
        ServiceRegisterInfo serviceRegisterInfo = new ServiceRegisterInfo(UserService.class.getName(),
                UserServiceImpl.class);
        // 服务提供者初始化
        ProviderBootstrap.init(serviceRegisterInfoList);
    }
}
