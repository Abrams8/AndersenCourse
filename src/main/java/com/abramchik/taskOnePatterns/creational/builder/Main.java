package com.abramchik.taskOnePatterns.creational.builder;

public class Main {
    public static void main(String[] args) {
        Director director = new Director();
        director.setBuilder(new VitsitCardWebsiteBuilder());

        WebSite webSite = director.buildWebSite();
        System.out.println(webSite);
    }
}
