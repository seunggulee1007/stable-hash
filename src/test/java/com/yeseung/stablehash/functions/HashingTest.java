package com.yeseung.stablehash.functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HashingTest {

    @Test
    void hash() {
        Hashing hashing = key -> {
            int intKey = Integer.parseInt(key.toString());
            return intKey * 2;
        };

        assertEquals(2, hashing.hash(1));
        assertThrows(NumberFormatException.class, () -> hashing.hash("a"));

    }
}