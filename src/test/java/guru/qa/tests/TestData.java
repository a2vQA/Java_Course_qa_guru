package guru.qa.tests;

import com.github.javafaker.Faker;
import guru.qa.utils.RandomUtils;

import static guru.qa.utils.RandomUtils.getCity;
import static guru.qa.utils.RandomUtils.getPlainOrSeparateRandomPhoneWOCode;
import static guru.qa.utils.RandomUtils.getRandomAddress;
import static guru.qa.utils.RandomUtils.getRandomGender;
import static guru.qa.utils.RandomUtils.getRandomHobby;
import static guru.qa.utils.RandomUtils.getRandomInt;
import static guru.qa.utils.RandomUtils.getRandomMonth;
import static guru.qa.utils.RandomUtils.getRandomSubject;
import static guru.qa.utils.RandomUtils.getStateByCity;

public class TestData {
    static Faker faker = new Faker();
    public static String FIRST_NAME = faker.name().firstName(),
            LAST_NAME = faker.name().lastName(),
            EMAIL = faker.internet().emailAddress(),
            PHONE_NUMBER = getPlainOrSeparateRandomPhoneWOCode(true),
            ADDRESS = getRandomAddress(),
            IMG_NAME = "testImg.jpg",
            GENDER = getRandomGender(),
            SUBJECT = getRandomSubject(),
            HOBBY = getRandomHobby(),
            CITY = getCity(),
            STATE = getStateByCity(CITY),
            BIRTH_DAY = String.valueOf(getRandomInt(1, 28)),
            BIRTH_MONTH = getRandomMonth(),
            BIRTH_YEAR = String.valueOf(getRandomInt(1950, 2000));
}
