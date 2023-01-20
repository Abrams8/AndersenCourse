package com.abramchik.taskOnePatterns.behavioral.command;

public class UpdateCommand implements Command{

    private DataBase dataBase;

    public UpdateCommand(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public void execute() {
        dataBase.uptade();
    }
}
