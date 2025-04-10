package com.redhat.training.greeter.service;
import jakarta.enterprise.context.Dependent;

@English
@Dependent
public class EnglishGreeter implements Greeter {
    @Override
    public String greet(String input) {
        return "Hello, " + input;
    }
}
