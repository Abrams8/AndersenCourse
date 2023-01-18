package com.abramchik.TaskOnePatterns.Creational.AbstractFactory.banking;

import com.abramchik.TaskOnePatterns.Creational.AbstractFactory.Developer;
import com.abramchik.TaskOnePatterns.Creational.AbstractFactory.Manager;
import com.abramchik.TaskOnePatterns.Creational.AbstractFactory.ProjectTeamFactory;
import com.abramchik.TaskOnePatterns.Creational.AbstractFactory.Tester;

public class BankTeamFactory implements ProjectTeamFactory {
    @Override
    public Developer getDeveloper() {
        return new BankDeveloper();
    }

    @Override
    public Tester getTester() {
        return new BankTester();
    }

    @Override
    public Manager getManager() {
        return new BankManager();
    }
}
