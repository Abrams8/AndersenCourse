package TaskOnePatterns.Creational.AbstractFactory.banking;

import TaskOnePatterns.Creational.AbstractFactory.Developer;

public class BankDeveloper implements Developer {
    @Override
    public void writeCode() {
        System.out.println("BankDev writes code...");
    }
}