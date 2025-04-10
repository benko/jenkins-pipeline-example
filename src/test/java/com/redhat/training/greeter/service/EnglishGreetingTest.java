package com.redhat.training.greeter.service;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;

@QuarkusTest
public class EnglishGreetingTest {
    @Inject
    @English
    Greeter g;

    @Test
    public void testGreeting() {
        GreeterTest.testGreeting(g, "John", "Hello, John");
    }
}
