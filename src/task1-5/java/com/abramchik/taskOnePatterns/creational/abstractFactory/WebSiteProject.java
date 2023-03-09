package com.abramchik.taskOnePatterns.creational.abstractFactory;

import com.abramchik.taskOnePatterns.creational.abstractFactory.banking.BankTeamFactory;
import com.abramchik.taskOnePatterns.creational.abstractFactory.website.WebSiteTeamFactory;

public class WebSiteProject {

    public static void main(String[] args) {
        ProjectTeamFactory projectTeamFactory = new WebSiteTeamFactory();
        Developer developer = projectTeamFactory.getDeveloper();
        Tester tester = projectTeamFactory.getTester();
        Manager manager = projectTeamFactory.getManager();

        developer.writeCode();
        tester.testCode();
        manager.manage();

        System.out.println("----------");

        ProjectTeamFactory bankTeamFactory = new BankTeamFactory();
        Developer developer1 = bankTeamFactory.getDeveloper();
        Tester tester1 = bankTeamFactory.getTester();
        Manager manager1 = bankTeamFactory.getManager();

        developer1.writeCode();
        tester1.testCode();
        manager1.manage();
    }


}
