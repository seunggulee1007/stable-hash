package com.yeseung.stablehash.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ServerTest {

    @Test
    @DisplayName("서버에 리소스를 추가하고 조회한다.")
    void addResource() {
        Server server = new Server();
        String key = "key";
        server.addResource(key, "value");
        assertEquals("value", server.getResource(key));
    }

}