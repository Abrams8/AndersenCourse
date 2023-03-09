package com.abramchik.taskOnePatterns.behavioral.command;

public class Main {
    public static void main(String[] args) {
        DataBase dataBase = new DataBase();
        Developer developer = new Developer(
                new InsertCommand(dataBase),
                new UpdateCommand(dataBase),
                new DeleteCommand(dataBase),
                new SelectCommand(dataBase));

        developer.insertRecord();
        developer.deleteRecord();
        developer.updateRecord();
        developer.selectRecord();
    }
}
