package com.abramchik.taskOnePatterns.structural.decorator;

public class Main {
    public static void main(String[] args) {
        Developer developer = new TeamLead(new SeniorDeveloper(new JavaDeveloper()));
        System.out.println(developer.makeJob());
    }
}
