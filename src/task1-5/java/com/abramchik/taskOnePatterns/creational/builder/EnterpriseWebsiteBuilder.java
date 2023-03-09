package com.abramchik.taskOnePatterns.creational.builder;

public class EnterpriseWebsiteBuilder extends WebSiteBuilder {
    @Override
    void buildName() {
        webSite.setName("Enterprise web site");
    }

    @Override
    void buildCms() {
        webSite.setCms(Cms.ALFRESCO);
    }

    @Override
    void buildPrice() {
        webSite.setPrice(5000);
    }
}
