package guru.qa.main.utils;

import com.github.javafaker.Faker;

import java.util.HashMap;
import java.util.Locale;

public class RandomUtils {

    public static int getRandomInt(int min, int max) {
        Faker faker = new Faker();
        return faker.number().numberBetween(min, max + 1);
    }

    public static String getRandomAddress() {
        Faker faker = new Faker();
        return faker.address().fullAddress();
    }

    public static String getPlainOrSeparateRandomPhoneWOCode(boolean plain) {
        Faker faker = new Faker(new Locale("ru"));
        if (plain) {
            return "9" + faker.phoneNumber().subscriberNumber(9);
        }
        return faker.phoneNumber().phoneNumber();
    }

    public static String getRandomGender() {
        Faker faker = new Faker();
        String[] genders = {"Male", "Female", "Other"};
        return faker.options().option(genders);
    }

    public static String getRandomSubject() {
        Faker faker = new Faker();
        String[] subjects = {"Hindi", "English", "Maths", "Physics", "Chemistry", "Biology", "Computer Science",
                "Commerce", "Accounting", "Economics", "Arts", "Social Studies", "History", "Civics"};
        return faker.options().option(subjects);
    }

    public static String getRandomHobby() {
        Faker faker = new Faker();
        String[] hobbies = {"Sports", "Reading", "Music"};
        return faker.options().option(hobbies);
    }

    public static String getCity() {
        Faker faker = new Faker();
        String[] cities = {"Delhi", "Agra", "Karnal", "Gurgaon", "Lucknow", "Panipat", "Jaipur", "Jaiselmer"};
        return faker.options().option(cities);
    }

    public static String getStateByCity(String value) {
        HashMap<String, String> cityAndState = new HashMap<>();
        cityAndState.put("Delhi", "NCR");
        cityAndState.put("Gurgaon", "NCR");
        cityAndState.put("Agra", "Uttar Pradesh");
        cityAndState.put("Lucknow", "Uttar Pradesh");
        cityAndState.put("Karnal", "Haryana");
        cityAndState.put("Panipat", "Haryana");
        cityAndState.put("Jaipur", "Rajasthan");
        cityAndState.put("Jaiselmer", "Rajasthan");
        return cityAndState.get(value);
    }

    public static String getRandomMonth() {
        Faker faker = new Faker();
        String[] months = {"January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"};
        return faker.options().option(months);
    }
}
