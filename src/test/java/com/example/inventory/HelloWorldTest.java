package com.example.inventory;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;

class HelloWorldTest {
    @Test
    void stringHelloName() {
        assertEquals("Hello Ostap", HelloWorld.printName("Ostap"));
    }

    @Test
    void stringHelloName2() {
        assertEquals("Hello Sergii", HelloWorld.printName("Sergii"));
    }
}