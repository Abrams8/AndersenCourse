package com.abramchik.taskOnePatterns.structural.decorator;

public class TeamLead extends DeveloperDecorator {
    public TeamLead(Developer developer) {
        super(developer);
    }

    public String manage() {
        return " TeamLead manages project";
    }

    @Override
    public String makeJob() {
        return super.makeJob() + manage();
    }
}
