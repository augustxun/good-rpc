package augustxun.rpc.loadbalancer;

import augustxun.rpc.model.ServiceMetaInfo;

import java.util.List;
import java.util.Map;

/**
 * 负载均衡器
 */
public interface LoadBalancer {
    /**
     * 选择服务调用
     * @param requestPara
     * @param serviceMetaInfoList
     * @return
     */
    ServiceMetaInfo select(Map<String, Object> requestPara, List<ServiceMetaInfo> serviceMetaInfoList);
}
