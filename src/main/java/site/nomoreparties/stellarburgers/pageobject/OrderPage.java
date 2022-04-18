package site.nomoreparties.stellarburgers.pageobject;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class OrderPage {

    @FindBy(how = How.XPATH, using = "//*[@id='root']//*[@class = 'text text_type_main-default' and text()='Булки']")
    private SelenideElement breadButton;

    @FindBy(how = How.XPATH, using = "//*[@id='root']//*[@class = 'text text_type_main-default' and text()='Соусы']")
    private SelenideElement sauceButton;

    @FindBy(how = How.XPATH, using = "//*[@id='root']//*[@class = 'text text_type_main-default' and text()='Начинки']")
    private SelenideElement fillingsButton;

    @Step("test if web element is active")
    public boolean isActive(String element) throws NoSuchElementException {

        String xpath = "//*[@class='text text_type_main-default' and text()='" + element + "']/parent::*";
        String status;

        try {
            status = Selenide.$(By.xpath(xpath)).getAttribute("class");
        } catch (NoSuchElementException e) {
            return false;
        }
        if (status.contains("tab_tab_type_current__2BEPc")) {
            return true;
        } else {
            return false;
        }
    }

    @Step("click on buns button")
    public OrderPage clickBreadButton() {
        sauceButton.click(); //to remove focus from bread button - otherwise not clickable
        breadButton.click();
        return this;
    }

    @Step("test if buns button selected")
    public boolean isBreadButtonActive() {
        return isActive("Булки");
    }

    @Step("click on sauces button")
    public OrderPage clickSauceButton() {
        sauceButton.click();
        return this;
    }

    @Step("test if sauces button selected")
    public boolean isSauceButtonActive() {
        return isActive("Соусы");
    }

    @Step("click on fillings button")
    public OrderPage clickFillingsButton() {
        fillingsButton.click();
        return this;
    }

    @Step("test if fillings button selected")
    public boolean isFillingsButtonActive() {
        return isActive("Начинки");
    }
}