package augustxun.rpc.config;

import augustxun.rpc.fault.retry.RetryStrategyKeys;
import augustxun.rpc.fault.tolerant.TolerantStrategyKeys;
import augustxun.rpc.loadbalancer.LoadBalancerKeys;
import augustxun.rpc.serializer.SerializerKeys;
import lombok.Data;

/**
 * RPC 框架全局配置
 */
@Data
public class RpcConfig {
    /**
     * 名称
     */
    private String name = "xun-rpc";
    /**
     * 版本号
     */
    private String version = "1.0";
    /**
     * 服务器主机名
     */
    private String serverHost = "localhost";
    /**
     * 服务器端口号
     */
    private Integer serverPort = 8089;
    /**
     * 模拟调用
     */
    private boolean mock = false;
    /**
     * 序列化器
     */
    private String serializer = SerializerKeys.JDK;
    /**
     * 负载均衡器
     */
    private String loadBalancer = LoadBalancerKeys.ROUND_ROBIN;
    /**
     * 注册中心
     */
    private RegistryConfig registryConfig = new RegistryConfig();
    /**
     * 重试策略
     */
    private String retryStrategy = RetryStrategyKeys.NO;
    /**
     * 容错策略
     */
    private String tolerantStrategy = TolerantStrategyKeys.FAIL_FAST;
}
