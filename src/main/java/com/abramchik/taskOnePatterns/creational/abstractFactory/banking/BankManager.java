package com.abramchik.taskOnePatterns.creational.abstractFactory.banking;

import com.abramchik.taskOnePatterns.creational.abstractFactory.Manager;

public class BankManager implements Manager {
    @Override
    public void manage() {
        System.out.println("BankManager manages project...");
    }
}
