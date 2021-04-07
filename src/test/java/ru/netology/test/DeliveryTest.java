package ru.netology.test;

import com.codeborne.selenide.Condition;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.data.DataGenerator;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class DeliveryTest {
    @Test
    void shouldSuccessfulPlanAndReplanMeeting() {
        val validUser = DataGenerator.Registration.generateUser();
        val daysToAddForFirstMeeting = 4;
        val firstMeetingDate = DataGenerator.generateDate(daysToAddForFirstMeeting);
        val daysToAddForSecondMeeting = 7;
        val secondMeetingDate = DataGenerator.generateDate(daysToAddForSecondMeeting);
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue(validUser.getCity());
        $("[data-test-id=date] input").sendKeys(Keys.CONTROL + "A", Keys.DELETE);
        $("[data-test-id=date] input").setValue(firstMeetingDate);
        $("[data-test-id=name] input").setValue(validUser.getName());
        $("[data-test-id=phone] input").setValue(validUser.getPhone());
        $("[data-test-id=agreement]").click();
        $(byText("Запланировать")).click();
        $(".notification__content").shouldHave(Condition.exactText("Встреча успешно запланирована на " + firstMeetingDate));
        $(".notification__closer").click();
        $("[data-test-id=date] input").sendKeys(Keys.CONTROL + "A", Keys.DELETE);
        $("[data-test-id=date] input").setValue(secondMeetingDate);
        $(byText("Запланировать")).click();
        $(byText("Перепланировать")).click();
        $(".notification__content").shouldHave(Condition.exactText("Встреча успешно запланирована на " + secondMeetingDate));







    }
}
