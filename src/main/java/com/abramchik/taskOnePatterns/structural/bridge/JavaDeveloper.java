package com.abramchik.taskOnePatterns.structural.bridge;

public class JavaDeveloper implements Developer{
    @Override
    public void writeCode() {
        System.out.println("Java dev writes code...");
    }
}
