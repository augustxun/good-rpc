package augustxun.rpc.consumer;

import augustxun.rpc.config.RpcConfig;
import augustxun.rpc.utils.ConfigUtils;

public class EasyConsumer {
    public static void main(String[] args) {
        RpcConfig rpc= ConfigUtils.loadConfig(RpcConfig.class, "rpc");
        System.out.println(rpc);
    }
}
