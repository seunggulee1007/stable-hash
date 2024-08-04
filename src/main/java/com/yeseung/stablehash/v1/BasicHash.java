package com.yeseung.stablehash.v1;

import com.yeseung.stablehash.domain.Server;
import com.yeseung.stablehash.functions.Hashing;

import java.util.ArrayList;
import java.util.List;

public class BasicHash {

    private final List<Server> serverList = new ArrayList<>();

    public Hashing hash() {
        return key -> {
            int hashedNumber = djb2Hash(key.toString());
            return Math.abs(hashedNumber) % serverList.size();
        };
    }

    // djb2 해시 함수
    private int djb2Hash(String str) {
        if(serverList.isEmpty()) {
            throw new IllegalStateException("서버가 없습니다.");
        }
        int hash = 5381;
        for (int i = 0; i < str.length(); i++) {
            hash = ((hash << 5) + hash) + str.charAt(i);
        }
        return hash;
    }

    public void addServer(Server server) {
        serverList.add(server);
    }

    public void removeServer(Server server) {
        serverList.remove(server);
    }

}
