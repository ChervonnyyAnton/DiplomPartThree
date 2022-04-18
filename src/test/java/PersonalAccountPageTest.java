import site.nomoreparties.stellarburgers.dataprovider.TestData;
import site.nomoreparties.stellarburgers.pageobject.LoginPage;
import com.codeborne.selenide.WebDriverRunner;
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
public class PersonalAccountPageTest {

    TestData data;
    LoginPage page;
    String userMail = "cry@on.techno";
    String userPassword = "123456";

    private final String browser;

    public PersonalAccountPageTest(String browser) {
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

        data = new TestData();
        page = open(data.URL, LoginPage.class);
        page.clickPersonalAccountButton().signIn(userMail, userPassword);
    }

    @After
    public void cleanUp() {
        WebDriverRunner.closeWebDriver();
    }

    @Test
    public void EnterPersonalAccountPageWithPersonalAccountButtonTest() {
        page.clickPersonalAccountButton();
        Assert.assertTrue("can not enter personal account page with personal account button", page.isThereAnySaveButton());
    }

    @Test
    public void EnterConstructorFromPersonalAccountPageWithConstructorButtonTest() {
        page.clickPersonalAccountButton().clickOnConstructorButton();
        Assert.assertTrue("can not enter constructor page with constructor button", page.isThereAnyMakeOrderButton());
    }

    @Test
    public void CanLogOutFromPersonalAccountPageTest() {
        page.clickPersonalAccountButton().clickLogOutButton();
        Assert.assertTrue("can not enter constructor page with logout button", page.isThereAnyEnterButton());
    }

    @Test
    public void EnterConstructorFromPersonalAccountWithPageLogoTest() {
        page.clickPersonalAccountButton().clickOnPageLogo();
        Assert.assertTrue("can not enter constructor page with logo button", page.isThereAnyMakeOrderButton());
    }
}