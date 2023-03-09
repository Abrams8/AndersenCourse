package com.abramchik.taskOnePatterns.structural.proxy;

public class ProjectRunner {
    public static void main(String[] args) {
        Project project = new ProxyProject("///");
        project.run();
    }
}
