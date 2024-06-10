package augustxun.rpc.loadbalancer;

import augustxun.rpc.spi.SpiLoader;
import io.grpc.LoadBalancer;
import io.grpc.util.RoundRobinLoadBalancer;

/**
 * 负载均衡器工厂（工厂模式，用于获取负载均衡器对象）
 */
public class LoadBalancerFactory {

    /**
     * 默认负载均衡器
     */
    private static final LoadBalancer DEFAULT_LOAD_BALANCER = new RoundRobinLoadBalancer();

    static {
        SpiLoader.load(LoadBalancer.class);
    }

    /**
     * 获取实例
     *
     * @param key
     * @return
     */
    public static LoadBalancer getInstance(String key) {
        return SpiLoader.getInstance(LoadBalancer.class, key);
    }

}
