package augustxun.rpc.registry;

import augustxun.rpc.config.RegistryConfig;
import augustxun.rpc.model.ServiceMetaInfo;

import java.util.List;

public interface Registry {
    /**
     * 初始化
     * @param registryConfig
     */
    void init(RegistryConfig registryConfig);

    /**
     * 注册服务（服务端）
     * @param serviceMetaInfo
     * @throws Exception
     */
    void register(ServiceMetaInfo serviceMetaInfo) throws Exception;

    /**
     * 注销服务（服务端）
     * @param serviceMetaInfo
     */
    void unRegister(ServiceMetaInfo serviceMetaInfo);

    /**
     * 心跳检测（服务端）
     */
    void heartBeat();

    /**
     * 服务发现（获取某服务的所有节点，消费端）
     * @param serviceKey 服务键名
     * @return
     */
    List<ServiceMetaInfo> discovery(String serviceKey);

    /**
     * 监听（消费端）
     * @param serviceNodeKey
     */
    void watch(String serviceNodeKey);

    /**
     * 服务销毁
     */
    void destroy();


}
