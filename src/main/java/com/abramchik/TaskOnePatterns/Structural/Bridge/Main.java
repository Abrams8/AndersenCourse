package com.abramchik.TaskOnePatterns.Structural.Bridge;

public class Main {
    public static void main(String[] args) {
        Program[] programs = {new BankSystem(new JavaDeveloper()),
        new ExchangeSystem(new PhpDeveloper())};

        for(Program program: programs){
            program.developProgram();
        }
    }
}
