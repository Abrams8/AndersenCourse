package com.abramchik.taskOnePatterns.creational.abstractFactory.website;

import com.abramchik.taskOnePatterns.creational.abstractFactory.Developer;

public class WebSiteDeveloper implements Developer {
    @Override
    public void writeCode() {
        System.out.println("WebSiteDeveloper writes code...");
    }
}
