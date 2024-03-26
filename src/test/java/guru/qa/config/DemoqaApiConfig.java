package guru.qa.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:config/api.properties"
})
public interface DemoqaApiConfig extends Config {
    @Key("demoqa.baseUrl")
    String demoqaBaseUrl();
}