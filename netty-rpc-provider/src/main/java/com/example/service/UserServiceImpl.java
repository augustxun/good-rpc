package com.example.service;

import com.example.api.IUserService;
import com.example.spring.annotation.GpRemoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@GpRemoteService //表示讲当前服务发布成远程服务
@Slf4j
public class UserServiceImpl implements IUserService {
    @Override
    public String saveUser(String name) {
        log.info("begin saveUser:"+name);
        return "Save User Success!";
    }
}
