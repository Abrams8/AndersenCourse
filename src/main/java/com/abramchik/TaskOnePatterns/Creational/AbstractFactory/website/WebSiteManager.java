package com.abramchik.TaskOnePatterns.Creational.AbstractFactory.website;

import com.abramchik.TaskOnePatterns.Creational.AbstractFactory.Manager;

public class WebSiteManager implements Manager {
    @Override
    public void manage() {
        System.out.println("WebSiteManager manages project...");
    }
}
