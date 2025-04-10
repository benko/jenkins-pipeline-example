package com.redhat.training.greeter.service;
import jakarta.enterprise.context.Dependent;

@French
@Dependent
public class FrenchGreeter implements Greeter {
    @Override
    public String greet(String input) {
        return "Salut, " + input;
    }
}
