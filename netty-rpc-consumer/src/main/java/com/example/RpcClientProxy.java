package com.example;

import com.example.spring.reference.RpcInvokerProxy;

import java.lang.reflect.Proxy;


@Deprecated
public class RpcClientProxy {

  /*  public <T> T clientProxy(final Class<T> interfaceCls,final String host,final int port){
        return (T) Proxy.newProxyInstance
                (interfaceCls.getClassLoader(),
                        new Class<?>[]{interfaceCls},
                        new RpcInvokerProxy(host,port));
    }*/
}
