package guru.qa.main.tests;

import com.github.javafaker.Faker;

import static guru.qa.main.utils.RandomUtils.getCity;
import static guru.qa.main.utils.RandomUtils.getPlainOrSeparateRandomPhoneWOCode;
import static guru.qa.main.utils.RandomUtils.getRandomAddress;
import static guru.qa.main.utils.RandomUtils.getRandomGender;
import static guru.qa.main.utils.RandomUtils.getRandomHobby;
import static guru.qa.main.utils.RandomUtils.getRandomInt;
import static guru.qa.main.utils.RandomUtils.getRandomMonth;
import static guru.qa.main.utils.RandomUtils.getRandomSubject;
import static guru.qa.main.utils.RandomUtils.getStateByCity;

public class TestData {
    Faker faker = new Faker();
    public String firstName = faker.name().firstName(),
            lastName = faker.name().lastName(),
            email = faker.internet().emailAddress(),
            phoneNumber = getPlainOrSeparateRandomPhoneWOCode(true),
            address = getRandomAddress(),
            imgName = "testImg.jpg",
            gender = getRandomGender(),
            subject = getRandomSubject(),
            hobby = getRandomHobby(),
            city = getCity(),
            state = getStateByCity(city),
            birthDay = String.valueOf(getRandomInt(1, 28)),
            birthMonth = getRandomMonth(),
            birthYear = String.valueOf(getRandomInt(1950, 2000));
}
