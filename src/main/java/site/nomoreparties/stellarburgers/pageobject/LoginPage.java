package site.nomoreparties.stellarburgers.pageobject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;


public class LoginPage {

    @FindBy(how = How.XPATH, using = "//*[@id='root']//*[@class='AppHeader_header__logo__2D0X2']")
    private SelenideElement pageLogo;

    @FindBy(how = How.XPATH, using = "//*[@id='root']//*[text()='Оформить заказ']")
    private SelenideElement makeOrder;

    @FindBy(how = How.XPATH, using = "//*[@id='root']//*[text()='Личный Кабинет']")
    private SelenideElement personalAccount;

    @FindBy(how = How.XPATH, using = "//*[@id='root']//*[text()='Войти в аккаунт']")
    private SelenideElement accountButton;

    @FindBy(how = How.XPATH, using = "//*[@id='root']//fieldset[1]//input")
    private SelenideElement nameInputLogin;

    @FindBy(how = How.XPATH, using = "//*[@id='root']//fieldset[2]//input")
    private SelenideElement mailInputLogin;

    @FindBy(how = How.XPATH, using = "//*[@id='root']//fieldset[3]//input")
    private SelenideElement passwordInputLogin;

    @FindBy(how = How.XPATH, using = "//*[@id='root']//*[text()='Зарегистрироваться']")
    private SelenideElement registerLink;

    @FindBy(how = How.XPATH, using = "//*[@id='root']//*[text()='Зарегистрироваться']")
    private SelenideElement registerButton;

    @FindBy(how = How.XPATH, using = "//*[@id='root']//*[@class='input__error text_type_main-default']")
    private SelenideElement errorMessage;

    @FindBy(how = How.XPATH, using = "//*[@id='root']//*[text()='Некорректный пароль']")
    private SelenideElement incorrectPasswordError;

    @FindBy(how = How.XPATH, using = "//*[@id='root']//*[text()='Такой пользователь уже существует']")
    private SelenideElement userAlreadyExistsError;

    @FindBy(how = How.XPATH, using = "//*[@id='root']//*[text()='Войти']")
    private SelenideElement enterButton;

    @FindBy(how = How.XPATH, using = "//*[@id='root']//*[text()='Сохранить']")
    private SelenideElement saveButton;

    @FindBy(how = How.XPATH, using = "//*[@id='root']//*[text()='Восстановить пароль']")
    private SelenideElement restorePasswordLink;

    @FindBy(how = How.XPATH, using = "//*[@id='root']//*[@class = 'Auth_link__1fOlj' and text()='Войти']")
    private SelenideElement enterLink;

    @FindBy(how = How.XPATH, using = "//*[@id='root']//*[text()='Профиль']")
    private SelenideElement profileButton;

    @FindBy(how = How.XPATH, using = "//*[@id='root']//*[text()='Конструктор']")
    private SelenideElement constructorButton;

    @FindBy(how = How.XPATH, using = "//*[@id='root']//*[text()='Выход']")
    private SelenideElement logoutButton;

    @FindBy(how = How.XPATH, using = "//*[@id='root']//fieldset[1]//input")
    private SelenideElement mailInputSignIn;

    @FindBy(how = How.XPATH, using = "//*[@id='root']//fieldset[2]//input")
    private SelenideElement passwordInputSignIn;

    @Step("Test if web element is visible")
    public boolean isDisplayed(SelenideElement element) throws NoSuchElementException {
        try {
            element.should(Condition.exist);
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }

    @Step("click on logo at header")
    public LoginPage clickOnPageLogo() {
        pageLogo.click();
        return this;
    }
    @Step("Test if order button is visible")
    public boolean isThereAnyMakeOrderButton() {
        return isDisplayed(makeOrder);
    }

    @Step("click on personal account button")
    public LoginPage clickPersonalAccountButton() {
        personalAccount.click();
        return this;
    }

    @Step("click on enter account button")
    public LoginPage clickAccountButton() {
        accountButton.click();
        return this;
    }

    @Step("click on register link")
    public LoginPage clickRegisterLink() {
        registerLink.click();
        return this;
    }

    @Step("fill the registration form")
    public LoginPage registerUser(String userName, String userMail, String userPassword) {
        nameInputLogin.setValue(userName);
        mailInputLogin.setValue(userMail);
        passwordInputLogin.setValue(userPassword);
        registerButton.click();
        return this;
    }

    @Step("test if any error messages displayed")
    public boolean isErrorMessage() {
        return errorMessage.exists();
    }

    @Step("test if enter button is visible")
    public boolean isThereAnyEnterButton() {
        return isDisplayed(enterButton);
    }

    @Step("test if save button is visible")
    public boolean isThereAnySaveButton() {
        return isDisplayed(saveButton);
    }

    @Step("click on enter button")
    public LoginPage clickOnEnterButton() {
        enterButton.click();
        return this;
    }

    @Step("click on restore password button")
    public LoginPage clickOnRestorePasswordLink() {
        restorePasswordLink.click();
        return this;
    }

    @Step("click on logout button")
    public LoginPage clickLogOutButton() {
        logoutButton.click();
        return this;
    }

    @Step("enter login data and click on enter")
    public LoginPage signIn(String mail, String password) {
        mailInputSignIn.setValue(mail);
        passwordInputSignIn.setValue(password);
        enterButton.click();
        return this;
    }

    @Step("click on constructor button")
    public LoginPage clickOnConstructorButton() {
        constructorButton.click();
        return this;
    }
}