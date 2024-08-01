package com.example.controller;

import com.example.api.IUserService;
import com.example.spring.annotation.GpRemoteReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloController {

    @GpRemoteReference
    private IUserService userService;


    @GetMapping("/test")
    public String test(){
        return userService.saveUser("Mic");
    }


}
