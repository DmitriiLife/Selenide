package ru.netology;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selectors.byText;

public class CardDeliveryOrder {
    private RegistrationInfo registrationInfo;

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
        registrationInfo = DataGenerator.RegistrationCard.generateInfo("ru");
    }

    @Test
    void registerByCreditCard() {
        $("[data-test-id='city'] input").setValue(registrationInfo.getCity());
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        String date = DataGenerator.getDate(3);
        $("[data-test-id='date'] input").setValue(date);
        $("[data-test-id='name'] input").setValue(registrationInfo.getName());
        $("[data-test-id='phone'] input").setValue("+79111111111");
        $("[data-test-id='agreement'] .checkbox__box").click();
        $$("button").find(exactText("Забронировать")).click();
        $(withText("Успешно! Встреча успешно забронирована на " + date));
                //.shouldBe(visible);
        //$(byText("Забронировать")).click();
        //    .shouldBe(visible, Duration.ofSeconds(7));

    }

    @Test
    void registerByCreditCardTwoLetters() {
        $("[data-test-id='city'] input").setValue("Ку");
        $$(".menu-item__control").find(exactText("Курск")).click();
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        String date = DataGenerator.getDate(7);
        $("[data-test-id='date'] input").setValue(date);
        $("[data-test-id='name'] input").setValue(registrationInfo.getName());
        $("[data-test-id='phone'] input").setValue("+79111111111");
        $("[data-test-id='agreement'] .checkbox__box").click();
        $$("button").find(exactText("Забронировать")).click();
        $(withText("Успешно! Встреча успешно забронирована на " + date));
               // .shouldBe(visible);
    }
}
//$("[data-test-id='notification'] .