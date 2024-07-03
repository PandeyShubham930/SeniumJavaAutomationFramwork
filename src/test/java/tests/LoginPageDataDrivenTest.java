package tests;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.LoginPage;
import utilities.XLUtility;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class LoginPageDataDrivenTest extends BaseClass
{
    public LoginPageDataDrivenTest() throws FileNotFoundException
    {

    }

    @Test(priority = 1,dataProvider = "LoginPageData",dataProviderClass = LoginPageDataDrivenTest.class)
    public void loginPageDD(String username, String password) throws IOException, InterruptedException, AWTException {
        LoginPage lp = new LoginPage(driver);
        driver.get(baseURL);
        lp.setUsername(username);
        lp.setPassword(password);
        lp.clickOnLoginBtn();

        if(isAlertPresent())
        {
            driver.switchTo().alert().accept();//close alert
            logger.info("screenshot captured");
            driver.switchTo().defaultContent();
            logger.warn("login Failed >> User is not valid");
            Assert.assertTrue(false);
        }
        else
        {
            if(driver.getTitle().equals("GTPL Bank Manager HomePage"))
            {
                Assert.assertTrue(true);
                lp.clickOnLogOutBtn();
                driver.switchTo().alert().accept();
                driver.switchTo().defaultContent();
                logger.info("test case passed");
            }
            else
            {
                logger.info("test case failed");
                captureScreen(driver, "LoginPageDataDrivenTest");
                Assert.assertTrue(false);
            }
        }
    }

    @DataProvider(name ="LoginPageData")
    public String[][] getData() throws IOException {
        String path ="/Users/luckythecoder/IdeaProjects/InternetBankingProject/src/test/java/testData/UserData.xlsx";
       int rownum= XLUtility.getRowCount(path,"Sheet1");
       int collnum =XLUtility.getCellCount(path, "Sheet1", 1);

       String[][] loginData = new String[rownum][collnum];
       for(int i=1; i<=rownum; i++)
       {
           for(int j=0; j<collnum; j++)
           {
           loginData[i-1][j]= XLUtility.getCellData(path, "Sheet1", i,j);
           }
       }
        return loginData;
    }

    //user defined method to check is alert is present or not
    public boolean isAlertPresent()
    {
        try
        {
            driver.switchTo().alert();
            return true;
        }
        catch (NoAlertPresentException e)
        {
            return false;
        }
    }




}
