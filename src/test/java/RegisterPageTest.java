import site.nomoreparties.stellarburgers.dataprovider.TestData;
import site.nomoreparties.stellarburgers.pageobject.LoginPage;
import com.codeborne.selenide.WebDriverRunner;
import com.github.javafaker.Faker;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.setWebDriver;

@RunWith(Parameterized.class)
public class RegisterPageTest {

    Faker faker;
    TestData data;
    LoginPage page;
    UserOps user;
    String userName;
    String userMail;
    String userPassword = "123456";

    private final String browser;

    public RegisterPageTest(String browser) {
        this.browser = browser;
    }

    @Parameterized.Parameters
    public static Object[][] setBrowser() {
        return new Object[][]{
                {Base.DRIVER_PATH_ONE},
                {Base.DRIVER_PATH_TWO}
        };
    }

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", browser);
        WebDriver driver = new ChromeDriver();
        setWebDriver(driver);
        faker = new Faker();
        data = new TestData();
        user = new UserOps();
        userName = faker.name().firstName();
        userMail = userName + "@yandex.at";
        userPassword = "1234567890";
        page = open(data.URL, LoginPage.class);
    }

    @After
    public void cleanUp() {
        user.cleanUp(userMail, userPassword);
        WebDriverRunner.closeWebDriver();
    }

    @Test
    public void CanCreateNewUserTest() {
        page.clickPersonalAccountButton()
                .clickRegisterLink()
                .registerUser(userName, userMail, userPassword);

        Assert.assertTrue("can not register new user", page.isThereAnyEnterButton());
    }

    @Test
    public void InvalidPasswordThrowsErrorMessageTest() {
        page.clickPersonalAccountButton()
                .clickRegisterLink()
                .registerUser(userName, userMail, "1234");

        Assert.assertTrue("can register new user with invalid credentials", page.isErrorMessage());
    }
}