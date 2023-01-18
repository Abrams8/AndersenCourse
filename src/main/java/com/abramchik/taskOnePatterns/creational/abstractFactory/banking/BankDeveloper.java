package com.abramchik.taskOnePatterns.creational.abstractFactory.banking;

import com.abramchik.taskOnePatterns.creational.abstractFactory.Developer;

public class BankDeveloper implements Developer {
    @Override
    public void writeCode() {
        System.out.println("BankDev writes code...");
    }
}
