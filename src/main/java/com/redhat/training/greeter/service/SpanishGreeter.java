package com.redhat.training.greeter.service;
import jakarta.enterprise.context.Dependent;

@Spanish
@Dependent
public class SpanishGreeter implements Greeter {
    @Override
    public String greet(String input) {
        return "Hello, " + input;
    }
}
