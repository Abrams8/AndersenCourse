package TaskOnePatterns.Creational.AbstractFactory.website;

import TaskOnePatterns.Creational.AbstractFactory.Manager;

public class WebSiteManager implements Manager {
    @Override
    public void manage() {
        System.out.println("WebSiteManager manages project...");
    }
}
