package com.abramchik.TaskOnePatterns.Creational.AbstractFactory.banking;

import com.abramchik.TaskOnePatterns.Creational.AbstractFactory.Developer;

public class BankDeveloper implements Developer {
    @Override
    public void writeCode() {
        System.out.println("BankDev writes code...");
    }
}
