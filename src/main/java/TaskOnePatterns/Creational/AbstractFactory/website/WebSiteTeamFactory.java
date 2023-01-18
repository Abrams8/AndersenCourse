package TaskOnePatterns.Creational.AbstractFactory.website;

import TaskOnePatterns.Creational.AbstractFactory.Developer;
import TaskOnePatterns.Creational.AbstractFactory.Manager;
import TaskOnePatterns.Creational.AbstractFactory.ProjectTeamFactory;
import TaskOnePatterns.Creational.AbstractFactory.Tester;

public class WebSiteTeamFactory implements ProjectTeamFactory {
    @Override
    public Developer getDeveloper() {
        return new WebSiteDeveloper();
    }

    @Override
    public Tester getTester() {
        return new WebSiteTester();
    }

    @Override
    public Manager getManager() {
        return new WebSiteManager();
    }
}
