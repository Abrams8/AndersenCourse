package com.abramchik.TaskOnePatterns.Creational.AbstractFactory;

public interface ProjectTeamFactory {
    Developer getDeveloper();
    Tester getTester();
    Manager getManager();
}
