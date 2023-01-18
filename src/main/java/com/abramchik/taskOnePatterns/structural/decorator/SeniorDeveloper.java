package com.abramchik.taskOnePatterns.structural.decorator;

public class SeniorDeveloper extends DeveloperDecorator {
    public SeniorDeveloper(Developer developer) {
        super(developer);
    }

    public String sendReport() {
        return " Senior sent report";
    }

    @Override
    public String makeJob() {
        return super.makeJob() + sendReport();
    }
}
