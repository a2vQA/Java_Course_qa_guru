package guru.qa.config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:config/${driver}.properties",
        "classpath:config/local.properties"
})
public interface DriverConfig extends Config {

    @Key("browser.name")
    @DefaultValue("chrome")
    String browserName();

    @Key("browser.version")
    @DefaultValue("100.0")
    String browserVersion();

    @Key("browser.size")
    @DefaultValue("1920x1080")
    String browserSize();

    @Key("is.remote")
    @DefaultValue("false")
    Boolean isRemote();

    @Key("browser.remote.url")
    @DefaultValue("https://user1:1234@selenoid.autotests.cloud/wd/hub")
    String browserRemoteUrl();
}
