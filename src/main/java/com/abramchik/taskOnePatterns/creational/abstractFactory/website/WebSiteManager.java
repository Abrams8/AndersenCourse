package com.abramchik.taskOnePatterns.creational.abstractFactory.website;

import com.abramchik.taskOnePatterns.creational.abstractFactory.Manager;

public class WebSiteManager implements Manager {
    @Override
    public void manage() {
        System.out.println("WebSiteManager manages project...");
    }
}
