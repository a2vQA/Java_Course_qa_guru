package guru.qa.MainProject.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:config/api.properties"
})
public interface ApiConfig extends Config {
    @Key("reqres.baseUrl")
    String reqresBaseUrl();

    @Key("reqres.basePath")
    String reqresBasePath();
}
