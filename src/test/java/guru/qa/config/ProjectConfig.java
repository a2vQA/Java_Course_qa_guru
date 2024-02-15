package guru.qa.config;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:config/${env}.properties")
public interface ProjectConfig extends Config {
    @Key("student.first.name")
    String studentFirstName();

    @Key("student.last.name")
    String studentLastName();

    @Key("student.gender")
    String studentGender();

    @Key("student.phone.number")
    String studentPhoneNumber();
}
