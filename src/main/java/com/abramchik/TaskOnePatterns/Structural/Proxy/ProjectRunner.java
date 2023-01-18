package com.abramchik.TaskOnePatterns.Structural.Proxy;

public class ProjectRunner {
    public static void main(String[] args) {
        Project project = new ProxyProject("///");
        project.run();
    }
}
