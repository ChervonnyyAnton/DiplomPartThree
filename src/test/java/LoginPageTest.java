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
public class LoginPageTest {

    Faker faker;
    TestData data;
    LoginPage page;
    String userMail = "cry@on.techno";
    String userPassword = "123456";

    private final String browser;

    public LoginPageTest(String browser) {
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
        System.setProperty("webdriver.chrome.driver", browser); //chrome driver based browser
        WebDriver driver = new ChromeDriver();
        setWebDriver(driver);

        faker = new Faker();
        data = new TestData();
        page = open(data.URL, LoginPage.class);
    }

    @After
    public void cleanUp() {
        WebDriverRunner.closeWebDriver();
    }

    @Test
    public void SignInWithAccountButtonTest() {

        page.clickAccountButton();
        page.signIn(userMail, userPassword);
        Assert.assertTrue("can not enter with enter account button", page.isThereAnyMakeOrderButton());

    }

    @Test
    public void SignInWithPersonalAccountButtonTest() {
        page.clickPersonalAccountButton();
        page.signIn(userMail, userPassword);
        Assert.assertTrue("can not enter with enter personal account button", page.isThereAnyMakeOrderButton());
    }

    @Test
    public void SignInWithRegisterLinkTest() {
        page.clickPersonalAccountButton()
                .clickRegisterLink()
                .clickOnEnterButton();
        page.signIn(userMail, userPassword);
        Assert.assertTrue("can not register a new valid user with registration form", page.isThereAnyMakeOrderButton());
    }

    @Test
    public void SignInWithPasswordRecoveryLinkTest() {
        page.clickPersonalAccountButton()
                .clickOnRestorePasswordLink()
                .clickOnEnterButton();
        page.signIn(userMail, userPassword);
        Assert.assertTrue("can not enter with restore password button", page.isThereAnyMakeOrderButton());
    }
}