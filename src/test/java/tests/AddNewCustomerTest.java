package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AddNewCustomer;
import pageObjects.LoginPage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;

public class AddNewCustomerTest extends BaseClass {
    public AddNewCustomerTest() throws FileNotFoundException {
    }

    @Test(priority = 1)
    public void addNewCustTest() throws InterruptedException, IOException {

        driver.get("https://demo.guru99.com/V1/html/addcustomerpage.php");
        logger.info("URL is opened");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//        LoginPage lp = new LoginPage(driver);
//        lp.setUsername(username);
//        lp.setPassword(password);
//        lp.clickOnLoginBtn();
//        Thread.sleep(2000);
//        logger.info("user successfully logged in");
//
        AddNewCustomer ad = new AddNewCustomer(driver);
//        ad.clickOnNewCustomer();
        Thread.sleep(3000);
//        ad.clickCloseAddButton();
        logger.info("opened the new customer page");
        ad.setCustomerName("luckthecoder");
        ad.selectGender();
        ad.selectDOB("08", "07", "1992");
        ad.selectAddress("Bangalore karanataka");
        ad.setCity("Bangalore");
        ad.selectState("Karanataka");
        ad.setPin(123456);
        ad.setTellNo("9399811882");
        ad.setEmailID(generateEmail());
        ad.clickOnSubmitBtn();

        if (driver.getPageSource().contains("Connection failed: Access denied for user 'root'@'localhost'")) {
            logger.info("TestCase passed");
            Assert.assertTrue(true);
        } else {
            logger.info("TestCaseFailed");
            captureScreen(driver, "AddNewCustomer");
            Assert.assertTrue(false);
        }
    }
}
