package TaskOnePatterns.Creational.AbstractFactory.banking;

import TaskOnePatterns.Creational.AbstractFactory.Developer;
import TaskOnePatterns.Creational.AbstractFactory.Manager;
import TaskOnePatterns.Creational.AbstractFactory.ProjectTeamFactory;
import TaskOnePatterns.Creational.AbstractFactory.Tester;

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
