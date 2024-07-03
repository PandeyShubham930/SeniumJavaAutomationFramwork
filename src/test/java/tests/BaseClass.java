package tests;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import utilities.ReadConfig;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class BaseClass {
    ReadConfig readConfig = new ReadConfig();
    public String baseURL = readConfig.getApplicationURL();
    public String username = readConfig.getUsername();
    public String password = readConfig.getPassword();
    public WebDriver driver;
    public Logger logger;

    public BaseClass() throws FileNotFoundException {
    }

    @BeforeClass
    @Parameters("browser")
    public void setup(String br) {
        logger = Logger.getLogger("InternetBankingProject");
        PropertyConfigurator.configure("log4j.properties");
        logger.info("opening the browser");

        if (br.equalsIgnoreCase("Safari")) {
            driver = new SafariDriver();
            driver.manage().window().maximize();
        }
        else if (br.equalsIgnoreCase("Chrome")) {
            ChromeOptions options= new ChromeOptions();
            options.addArguments("--disable-notifications");
            options.addArguments("--incognito");
            driver = new ChromeDriver(options);
            driver.manage().window().maximize();
        }
        else if (br.equalsIgnoreCase("Firefox")) {
            driver = new FirefoxDriver();
            driver.manage().window().maximize();
        }
    }

    @AfterClass
    public void tearDown() {
        logger.info("closing the browser");
        driver.quit();
    }

    public void captureScreen(WebDriver driver, String tname) throws IOException {
        TakesScreenshot tk = (TakesScreenshot) driver;
        {
            File src= tk.getScreenshotAs(OutputType.FILE);
            File dest = new File("/Users/luckythecoder/IdeaProjects/InternetBankingProject/Screenshots/"+ tname+ ".png");
            FileUtils.copyFile(src,dest);
            System.out.println("Screenshot taken");
        }
    }

    public String generateEmail()
    {
        //return random generated email
        return RandomStringUtils.randomAlphabetic(5) +"@gmail.com";
    }
}
