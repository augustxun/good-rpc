package augustxun.rpc.bootstrap;

import augustxun.rpc.RpcHolder;
import augustxun.rpc.config.RegistryConfig;
import augustxun.rpc.config.RpcConfig;
import augustxun.rpc.model.ServiceMetaInfo;
import augustxun.rpc.model.ServiceRegisterInfo;
import augustxun.rpc.registry.LocalRegistry;
import augustxun.rpc.registry.Registry;
import augustxun.rpc.registry.RegistryFactory;
import augustxun.rpc.server.tcp.VertxTcpServer;

import java.util.List;

/**
 * 服务提供者初始化
 */
public class ProviderBootstrap {
    public static void init(List<ServiceRegisterInfo<?>> serviceRegisterInfoList) {
        // RPC框架初始化
        RpcHolder.init();
        // 全局配置
        final RpcConfig rpcConfig = RpcHolder.getRpcConfig();

        // 注册服务
        for (ServiceRegisterInfo<?> serviceRegisterInfo : serviceRegisterInfoList) {
            String serviceName = serviceRegisterInfo.getServiceName();
            // 本地注册
            LocalRegistry.register(serviceName, serviceRegisterInfo.getImplClass());
            // 注册服务到注册中心
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
        }
        // 启动Web服务
        VertxTcpServer vertxTcpServer = new VertxTcpServer();
        vertxTcpServer.doStart(rpcConfig.getServerPort());
    }
}
