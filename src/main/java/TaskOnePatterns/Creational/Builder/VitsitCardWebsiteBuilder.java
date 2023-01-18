package TaskOnePatterns.Creational.Builder;

public class VitsitCardWebsiteBuilder extends WebSiteBuilder {
    @Override
    void buildName() {
        webSite.setName("Visit card");
    }

    @Override
    void buildCms() {
        webSite.setCms(Cms.WORDPRESS);
    }

    @Override
    void buildPrice() {
        webSite.setPrice(1000);
    }
}
