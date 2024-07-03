package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddNewCustomer
{
    WebDriver driver;
    public AddNewCustomer(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//a[@href='addcustomerpage.php']")
    WebElement addNewCustLink;

    @FindBy(name = "name")
    WebElement customerName;

    @FindBy(xpath = "(//input[@type='radio'])[2]")
    WebElement gender;

    @FindBy(id = "dob")
    WebElement DOB;

    @FindBy(name = "addr")
    WebElement address;

    @FindBy(css = "input[onkeyup='validateCity();']")
    WebElement city;

    @FindBy(name = "state")
    WebElement state;

    @FindBy(name = "pinno")
    WebElement Pin;

    @FindBy(name = "telephoneno")
    WebElement tellNo;

    @FindBy(name = "emailid")
    WebElement emailID;

    @FindBy(xpath = "//input[@value='Submit']")
    WebElement submitBtn;

    public void clickOnNewCustomer()
    {
        addNewCustLink.click();
    }

    public void setCustomerName(String name)
    {
        customerName.sendKeys(name);
    }

    public void selectGender()
    {
        gender.click();
    }
    public void selectDOB(String mm, String dd, String yy) throws InterruptedException {
        DOB.sendKeys(mm);
        Thread.sleep(2000);
        DOB.sendKeys(dd);
        Thread.sleep(2000);
        DOB.sendKeys(yy);
    }

    public void selectAddress(String addressname)
    {
        address.sendKeys(addressname);
    }

    public void setCity(String cityname)
    {
        city.sendKeys(cityname);
    }


    public void selectState(String statename)
    {
        state.sendKeys(statename);
    }

    public void setPin(int pinno)
    {
       Pin.sendKeys(String.valueOf(pinno));//converting int to string
    }

    public void setTellNo(String tellnumber)
    {
        tellNo.sendKeys(String.valueOf(tellnumber));
    }

    public void setEmailID(String emailidno)
    {
        emailID.sendKeys(emailidno);
    }

    public void clickOnSubmitBtn()
    {
        submitBtn.click();
    }
}
