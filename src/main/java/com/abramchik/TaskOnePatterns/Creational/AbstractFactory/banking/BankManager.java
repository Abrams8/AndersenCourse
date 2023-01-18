package com.abramchik.TaskOnePatterns.Creational.AbstractFactory.banking;

import com.abramchik.TaskOnePatterns.Creational.AbstractFactory.Manager;

public class BankManager implements Manager {
    @Override
    public void manage() {
        System.out.println("BankManager manages project...");
    }
}
