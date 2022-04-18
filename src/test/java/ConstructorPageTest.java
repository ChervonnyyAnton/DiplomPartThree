import site.nomoreparties.stellarburgers.dataprovider.TestData;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import site.nomoreparties.stellarburgers.pageobject.OrderPage;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.setWebDriver;

@RunWith(Parameterized.class)
public class ConstructorPageTest {

    TestData data;
    OrderPage page;

    private final String browser;

    public ConstructorPageTest(String browser) {
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
        System.setProperty("webdriver.chrome.driver", browser); //based on experience here will be selected chrome-based browser
        WebDriver driver = new ChromeDriver();
        setWebDriver(driver);

        data = new TestData();
        page = open(data.URL, OrderPage.class);
    }

    @After
    public void cleanUp() {
        WebDriverRunner.closeWebDriver();
    }

    @Test
    public void ClickOnSauceButtonTest() {
        page.clickSauceButton();
        Assert.assertTrue(page.isSauceButtonActive());
    }

    @Test
    public void ClickOnFillingsButtonTest() {
        page.clickFillingsButton();
        Assert.assertTrue(page.isFillingsButtonActive());
    }

    @Test
    public void ClickOnBreadButtonTest() {
        page.clickBreadButton();
        Assert.assertTrue(page.isBreadButtonActive());
    }
}