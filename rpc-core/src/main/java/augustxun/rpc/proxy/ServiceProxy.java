package augustxun.rpc.proxy;

import augustxun.rpc.RpcApplication;
import augustxun.rpc.config.RpcConfig;
import augustxun.rpc.constant.RpcConstant;
import augustxun.rpc.model.RpcRequest;
import augustxun.rpc.model.RpcResponse;
import augustxun.rpc.model.ServiceMetaInfo;
import augustxun.rpc.registry.Registry;
import augustxun.rpc.registry.RegistryFactory;
import augustxun.rpc.serializer.Serializer;
import augustxun.rpc.serializer.SerializerFactory;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

/**
 * 服务代理（JDK动态代理）
 */
public class ServiceProxy implements InvocationHandler {
    /**
     * 调用代理
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 指定序列化器
        final Serializer serializer = SerializerFactory.getInstance(RpcApplication.getRpcConfig().getSerializer());
        // 构造请求
        String serviceName = method.getDeclaringClass().getName();
        RpcRequest rpcRequest = RpcRequest.builder()
                .serviceName(serviceName)
                .methodName(method.getName())
                .parameterTypes(method.getParameterTypes())
                .args(args)
                .build();
        byte[] bodyBytes = new byte[0];
        try {
            // 序列化
            bodyBytes = serializer.serialize(rpcRequest);
            // 从注册中心获取服务提供者请求的地址
            RpcConfig rpcConfig = RpcApplication.getRpcConfig();
            Registry registry = RegistryFactory.getInstance(rpcConfig.getRegistryConfig().getRegistry());
            ServiceMetaInfo serviceMetaInfo = new ServiceMetaInfo();
            serviceMetaInfo.setServiceName(serviceName);
            serviceMetaInfo.setServiceVersion(RpcConstant.DEFAULT_SERVICE_VERSION);
            List<ServiceMetaInfo> serviceMetaInfoList = registry.discovery(serviceMetaInfo.getServiceKey());
            if (CollUtil.isEmpty(serviceMetaInfoList)) {
                throw new RuntimeException("暂无服务地址");
            }
            ServiceMetaInfo selectedServiceMetaInfo = serviceMetaInfoList.get(0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // 发送请求
        try (HttpResponse httpResponse = HttpRequest.post("localhost:8089").body(bodyBytes).execute()) {
            byte[] result = httpResponse.bodyBytes();
            // 反序列化
            RpcResponse rpcResponse = serializer.deserialize(result, RpcResponse.class);
            return rpcResponse.getData();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
