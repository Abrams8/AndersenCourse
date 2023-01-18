package com.abramchik.TaskOnePatterns.Creational.AbstractFactory.website;

import com.abramchik.TaskOnePatterns.Creational.AbstractFactory.Developer;
import com.abramchik.TaskOnePatterns.Creational.AbstractFactory.Manager;
import com.abramchik.TaskOnePatterns.Creational.AbstractFactory.ProjectTeamFactory;
import com.abramchik.TaskOnePatterns.Creational.AbstractFactory.Tester;

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
