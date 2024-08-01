package com.example.spring.reference;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Data
public class RpcClientProperties {

    private String serviceAddress="192.168.1.102";

    private int servicePort=20880;

    private byte registryType;

    private String registryAddress;

}
