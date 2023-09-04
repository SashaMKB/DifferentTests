package ru.kai.testqa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class KaiMainPageTests extends BaseTest {
    @Test
    public void checkEnterInAuthFormTest() {
        driver.get("https://testqa.kai.ru/");
        //Находим кнопку вход
        WebElement enterButton = driver.findElement(By.xpath("//*[@id=\"banner\"]/ul/li[1]/a")); // <-- так делать нельзя

        enterButton.click();

        By buttonEnter = By.name("submit");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.presenceOfElementLocated(buttonEnter));

        Assert.assertTrue(driver.findElement(buttonEnter).isDisplayed());

    }

//    @Test
//    public void  checkLinkInEnterButtonTest() {
//        driver.get("https://testqa.kai.ru/");
//        //Находим кнопку вход
//        WebElement enterButton = driver.findElement(By.xpath("//*[@id=\"banner\"]/ul/li[1]/a"));
//        //ожидаемый url
//        String url = "http://testqa.kai.ru/index.php?mode=auth";
//        // актуальный url
//        String currentUrl = enterButton.getAttribute("href");
//        //проверка на то, что url атрибута href соответствует ожидаемому
//        Assert.assertEquals(url, currentUrl);
//    }

    @Test
    public void checkElementsInSideBar() {
        //открываем сттраницу стенда
        driver.navigate().to("https://testqa.kai.ru/");
        // получаем объект кнопки бокового меню
        WebElement elementSideBar = driver.findElement(By.xpath("//*[@href=\"#nav\"]"));
        // кликаем по кнопке
        elementSideBar.click();

        // крестик
        By crossButton = By.xpath("//*[@id=\"nav\"]/a"); // <-- так делать нельзя
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfElementLocated(crossButton));
        // главная
        By mainButton = By.xpath("//*[@id=\"nav\"]/ul/li[1]/a"); // <-- так делать нельзя
        wait.until(ExpectedConditions.presenceOfElementLocated(mainButton));
        // books api
        By booksApiButton = By.xpath("//*[@id=\"nav\"]/ul/li[2]/a");// <-- так делать нельзя
        wait.until(ExpectedConditions.presenceOfElementLocated(booksApiButton));


        Assert.assertTrue(driver.findElement(crossButton).isDisplayed());
        Assert.assertTrue(driver.findElement(mainButton).isDisplayed());
        Assert.assertTrue(driver.findElement(booksApiButton).isDisplayed());
    }

//    @Test
//    public void checkErrorMessageWithIncorrectPasswordTest() {
//        String email = "test@test.ru";
//        String password = "qwerty";
//
//        By inputEmail = By.name("email");
//        By inputPassword = By.name("pass");
//        By buttonEnter = By.name("submit");
//
//        By errorMessage = By.xpath("//*[@id=\"content\"]/ul/li");
//
//
//        driver.get("https://testqa.kai.ru/index.php?mode=auth");
//        driver.findElement(inputEmail).sendKeys(email);
//
//        driver.findElement(inputPassword).sendKeys(password);
//
//        driver.findElement(buttonEnter).click();
//
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//
//        Assert.assertTrue(
//                wait.until(ExpectedConditions.presenceOfElementLocated(errorMessage))
//                        .isDisplayed()
//        );
//    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
