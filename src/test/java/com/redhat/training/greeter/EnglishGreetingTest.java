package com.redhat.training.greeter;

import org.junit.jupiter.api.Test;

import com.redhat.training.greeter.service.English;
import com.redhat.training.greeter.service.Greeter;

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
