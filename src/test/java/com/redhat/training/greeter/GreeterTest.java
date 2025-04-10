package com.redhat.training.greeter;
import static org.junit.jupiter.api.Assertions.*;

import com.redhat.training.greeter.service.Greeter;

public class GreeterTest {
    public static void testGreeting(Greeter g, String input, String expectation) {
        String result = g.greet(input);
        assertEquals(expectation, result);
    }
}
