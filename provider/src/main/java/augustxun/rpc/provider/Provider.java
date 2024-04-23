package augustxun.rpc.provider;

import augustxun.rpc.RpcApplication;
import augustxun.rpc.common.service.UserService;
import augustxun.rpc.registry.LocalRegistry;
import augustxun.rpc.server.HttpServer;
import augustxun.rpc.server.VertxHttpServer;

public class Provider {
    public static void main(String[] args) {

        // RPC 框架初始化
        RpcApplication.init();

        // 注册服务
        LocalRegistry.register(UserService.class.getName(), UserServiceImpl.class);

        // 启动 Web 服务
        HttpServer httpServer=new VertxHttpServer();
        httpServer.doStart(RpcApplication.getRpcConfig().getServerPort());
    }
}
