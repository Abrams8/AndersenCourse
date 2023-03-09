package com.abramchik.taskOnePatterns.creational.abstractFactory.website;

import com.abramchik.taskOnePatterns.creational.abstractFactory.Developer;
import com.abramchik.taskOnePatterns.creational.abstractFactory.Manager;
import com.abramchik.taskOnePatterns.creational.abstractFactory.ProjectTeamFactory;
import com.abramchik.taskOnePatterns.creational.abstractFactory.Tester;

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
