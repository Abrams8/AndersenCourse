package com.abramchik.taskOnePatterns.structural.bridge;

public class ExchangeSystem extends Program{

    public ExchangeSystem(Developer developer) {
        super(developer);
    }

    @Override
    public void developProgram() {
        System.out.println("Exchange development in progress...");
        developer.writeCode();
    }
}
