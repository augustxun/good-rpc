package augustxun.rpc.fault.retry;

import augustxun.rpc.model.RpcResponse;

import java.util.concurrent.Callable;

public interface RetryStrategy {
    /**
     * 重试
     * @param callable
     * @return
     * @throws Exception
     */
    RpcResponse doRetry(Callable<RpcResponse> callable) throws Exception;
}
