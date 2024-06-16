package augustxun.rpc.provider;

import augustxun.rpc.RpcApplication;
import augustxun.rpc.common.service.UserService;
import augustxun.rpc.config.RegistryConfig;
import augustxun.rpc.config.RpcConfig;
import augustxun.rpc.constant.RpcConstant;
import augustxun.rpc.model.ServiceMetaInfo;
import augustxun.rpc.registry.LocalRegistry;
import augustxun.rpc.registry.Registry;
import augustxun.rpc.registry.RegistryFactory;
import augustxun.rpc.server.HttpServer;
import augustxun.rpc.server.VertxHttpServer;

/**
 * 服务提供者示例
 */
public class ProviderExample {
    public static void main(String[] args) {
        // RPC框架初始化
        RpcApplication.init();

        // 注册服务
        String serviceName = UserService.class.getName();
        LocalRegistry.register(serviceName, UserServiceImpl.class);

        // 注册服务到注册中心
        RpcConfig rpcConfig = RpcApplication.getRpcConfig();
        RegistryConfig registryConfig = rpcConfig.getRegistryConfig();
        Registry registry = RegistryFactory.getInstance(registryConfig.getRegistry());
        ServiceMetaInfo serviceMetaInfo = new ServiceMetaInfo();
        serviceMetaInfo.setServiceName(serviceName);
        serviceMetaInfo.setServiceHost(rpcConfig.getServerHost());
        serviceMetaInfo.setServicePort(rpcConfig.getServerPort());
        try {
            registry.register(serviceMetaInfo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // 启动Web服务
        HttpServer httpServer = new VertxHttpServer();
        httpServer.doStart(RpcApplication.getRpcConfig().getServerPort());
    }
}
