package guru.qa.utils;

import com.github.javafaker.Faker;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

public class RandomUtils {

    public static void main(String[] args){
        System.out.println(getRandomGender());
        System.out.println(getStateByCity(getCity()));
    }

    public static int getRandomInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    public static String getRandomAddress() {
        return RandomStringUtils.randomAlphabetic(10) + ", " + RandomStringUtils.randomAlphabetic(10);
    }

    public static String getPlainOrSeparateRandomPhoneWOCode(boolean plain) {
        if (plain) {
            return getRandomInt(10000, 99999) + "" + getRandomInt(10000, 99999);
        }
        return String.format("(%d)%d-%d-%d", getRandomInt(100, 999), getRandomInt(100, 999),
                getRandomInt(10, 99), getRandomInt(10, 99));
    }

    public static String getRandomGender() {
        String[] genders = {"Male", "Female", "Other"};
        int index = getRandomInt(0, genders.length - 1);
        return genders[index];
    }

    public static String getRandomSubject() {
        String[] subjects = {"Hindi", "English", "Maths", "Physics", "Chemistry", "Biology", "Computer Science",
                "Commerce", "Accounting", "Economics", "Arts", "Social Studies", "History", "Civics"};
        int index = getRandomInt(0, subjects.length - 1);
        return subjects[index];
    }

    public static String getRandomHobby() {
        String[] hobbies = {"Sports", "Reading", "Music"};
        int index = getRandomInt(0, hobbies.length - 1);
        return hobbies[index];
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
