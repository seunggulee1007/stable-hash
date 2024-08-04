package com.yeseung.stablehash.domain;

import java.util.HashMap;
import java.util.Map;

public class Server {

    private final Map<Object, Object> serverMap = new HashMap<>();

    public void addResource(Object key, Object value) {
        serverMap.put(key, value);
    }

    public Object getResource(Object key) {
        return serverMap.get(key);
    }

}
