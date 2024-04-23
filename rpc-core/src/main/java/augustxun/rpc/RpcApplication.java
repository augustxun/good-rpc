package augustxun.rpc;

import augustxun.rpc.config.RpcConfig;
import augustxun.rpc.constant.RpcConstant;
import augustxun.rpc.utils.ConfigUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * RPC框架应用
 * 相当于 holder，存放了项目全局用到的变量。双检验锁单例模式实现
 */
@Slf4j
public class RpcApplication {
    private static volatile RpcConfig config;

    public static void init(RpcConfig newRpcConfig) {
        config = newRpcConfig;
        log.info("rpc init, config={}", new RpcConfig().toString());
    }

    /**
     * 初始化
     */
    public static void init() {
        RpcConfig newRpcConfig;
        try {
            newRpcConfig = ConfigUtils.loadConfig(RpcConfig.class, RpcConstant.DEFAULT_CONFIG_PREFIX);
        } catch (Exception e) {
            // 配置加载失败，使用默认值
            newRpcConfig = new RpcConfig();
        }
        init(newRpcConfig);
    }

    /**
     * 获取配置
     * 懒加载机制
     */
    public static RpcConfig getRpcConfig() {
        if (config == null) {
            synchronized (RpcApplication.class){
                if(config==null) {
                    init();
                }
            }
        }
        return config;
    }

}
