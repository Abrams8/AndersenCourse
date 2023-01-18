package com.abramchik.taskOnePatterns.creational.abstractFactory.banking;

import com.abramchik.taskOnePatterns.creational.abstractFactory.Developer;
import com.abramchik.taskOnePatterns.creational.abstractFactory.Manager;
import com.abramchik.taskOnePatterns.creational.abstractFactory.ProjectTeamFactory;
import com.abramchik.taskOnePatterns.creational.abstractFactory.Tester;

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
