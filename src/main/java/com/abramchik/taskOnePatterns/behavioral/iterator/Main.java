package com.abramchik.taskOnePatterns.behavioral.iterator;

public class Main {

    public static void main(String[] args) {
        String[] skills = {"Java", "Spring", "Maven", "MySQL", "Hibernate"};

        JavaDeveloper javaDeveloper = new JavaDeveloper("Abama", skills);

        Iterator iterator = javaDeveloper.getIterator();

        System.out.println("Developer: " + javaDeveloper.getName());
        System.out.println("Skills: ");

        while (iterator.hasNext()) {
            System.out.print(iterator.next().toString() + " ");
        }
    }
}
