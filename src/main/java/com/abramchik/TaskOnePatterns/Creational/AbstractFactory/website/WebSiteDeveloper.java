package com.abramchik.TaskOnePatterns.Creational.AbstractFactory.website;

import com.abramchik.TaskOnePatterns.Creational.AbstractFactory.Developer;

public class WebSiteDeveloper implements Developer {
    @Override
    public void writeCode() {
        System.out.println("WebSiteDeveloper writes code...");
    }
}
