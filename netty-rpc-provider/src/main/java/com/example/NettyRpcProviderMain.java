package com.example;

import com.example.protocol.NettyServer;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@ComponentScan(basePackages = {"com.example.spring.annotation","com.example.spring.service","com.example.service"})
@SpringBootApplication
public class NettyRpcProviderMain {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(NettyRpcProviderMain.class, args);
    }
}
