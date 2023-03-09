package com.abramchik.taskOnePatterns.behavioral.command;

public class SelectCommand implements Command{
    private DataBase dataBase;

    public SelectCommand(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public void execute() {
        dataBase.select();
    }
}
