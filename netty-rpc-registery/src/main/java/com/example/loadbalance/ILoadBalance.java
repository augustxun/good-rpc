package com.example.loadbalance;

import java.util.List;


public interface ILoadBalance<T> {

    T select(List<T> servers);
}
