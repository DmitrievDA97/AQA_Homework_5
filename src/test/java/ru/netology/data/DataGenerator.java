package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DataGenerator {
    private DataGenerator() {
    }

    public static Faker faker = new Faker(new Locale("ru"));

    public static String generateDate(int shift) {
        Calendar currentDate = Calendar.getInstance();
        currentDate.add(Calendar.DATE, shift);
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        return dateFormat.format(currentDate.getTime());

    }

    public static String generateCity() {
        return faker.address().city();
    }

    public static String generateName() {
        return faker.name().lastName().replace("ё", "е") + " " + faker.name().firstName().replace("ё", "е");
    }

    public static String generatePhone() {
        return faker.phoneNumber().phoneNumber();
    }

    public static class Registration {
        private Registration() {
        }

        public static UserInfo generateUser() {
        UserInfo user = new UserInfo(generateCity(), generateName(), generatePhone());
        return user;
        }
}

    @Value
    public static class UserInfo {
        String city;
        String name;
        String phone;
    }
}
