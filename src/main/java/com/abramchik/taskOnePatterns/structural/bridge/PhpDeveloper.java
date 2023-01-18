package com.abramchik.taskOnePatterns.structural.bridge;

public class PhpDeveloper implements Developer{
    @Override
    public void writeCode() {
        System.out.println("PHP dev writes code...");
    }
}
