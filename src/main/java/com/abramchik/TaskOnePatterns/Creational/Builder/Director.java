package com.abramchik.TaskOnePatterns.Creational.Builder;

public class Director {
    WebSiteBuilder builder;

    public void setBuilder(WebSiteBuilder builder){
        this.builder = builder;
    }

    WebSite buildWebSite(){
        builder.createWebSite();
        builder.buildName();
        builder.buildCms();
        builder.buildPrice();

        WebSite webSite = builder.getWebSite();

        return webSite;
    }

}
