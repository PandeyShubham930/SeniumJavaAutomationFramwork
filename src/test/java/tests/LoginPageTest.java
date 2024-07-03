package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.LoginPage;

import java.io.FileNotFoundException;
import java.io.IOException;

public class LoginPageTest extends BaseClass {
    public LoginPageTest() throws FileNotFoundException {
    }

    @Test(priority = 1)
    public void loginTest() throws InterruptedException, IOException {
        driver.get(baseURL);
        logger.info("URL is opened");

        LoginPage lp = new LoginPage(driver);

        lp.setUsername(username);
        logger.info("Entered username");
        lp.setPassword(password);
        logger.info("Entered password");
        lp.clickOnLoginBtn();
        logger.info("clicked on login button");
        Thread.sleep(3000);

        if (driver.getTitle().equals("GTPL Bank Manager HomePage")) {
            Assert.assertTrue(true);
            logger.info("test case is passed");
        }
        else
        {
            captureScreen(driver,"loginTest");
            logger.info("test case is failed");
            Assert.assertTrue(false);
        }
    }
}
