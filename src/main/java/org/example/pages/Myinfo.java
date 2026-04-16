package org.example.pages;
import org.example.utils.PropertyReader;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.example.utils.waithelpers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Myinfo {
    WebDriver driver;
    private static final Logger log = LoggerFactory.getLogger(Myinfo.class);
    public Myinfo(WebDriver driver){
        this.driver=driver;
    }
    private By myinfo= By.xpath("//span[text()=\"My Info\"]");
    private By personaldetails=By.xpath("//h6[text()=\"Personal Details\"]");
    private By fristname = By.xpath("//input[@name=\"firstName\"]");
    private By middlename= By.xpath("//input[@name=\"middleName\"]");
    private By lastname= By.xpath("//input[@name=\"lastName\"]");
    private By empid=By.xpath("//label[text()='Employee Id']/following::input[1]");
    private By otherid=By.xpath("//label[text()='Other Id']/following::input[1]");
    private By drivelicence=By.xpath("//label[text()=\"Driver's License Number\"]/following::input[1]");
    private By licence_exp=By.xpath("//input[@class=\"oxd-input oxd-input--focus\"]");
    private By nation=By.xpath("(//div[@class=\"oxd-select-wrapper\"])[1]");
    //(//div[@class="oxd-select-wrapper"]//i)[1]
    private By nationlist=By.xpath("//div[@role=\"listbox\"]//span[text()=\"Indian\"]");
    private By marragestatus=By.xpath("(//div[@class=\"oxd-select-wrapper\"])[2]");
    private By marstatus=By.xpath("(//div[@class=\"oxd-select-wrapper\"]//i)[2]/following::span[2]");
    private By dob=By.xpath("(//div[@class=\"oxd-date-input\"]//input)[2]");
    private By gender=By.xpath("//label[text()=\"Male\"]");
    private By savebtn=By.xpath("//button[@type=\"submit\"]");
    private By sucesstext=By.xpath("//p[text()=\"Success\"]");

    //Contact Details
   private By contactdetails=By.xpath("//a[text()=\"Contact Details\"]");
   private By address=By.xpath("//h6[text()=\"Address\"]");
   private By empfullname=By.xpath("//label[text()=\"Employee Full Name\"]");
   private By street1=By.xpath("//label[text()=\"Street 1\"]/following::input[1]");
   private By street2=By.xpath("//label[text()=\"Street 2\"]/following::input[1]");
   private By city=By.xpath("//label[text()=\"City\"]/following::input[1]");
   private By state=By.xpath("//label[text()=\"State/Province\"]/following::input[1]");
   private By zipcode=By.xpath("//label[text()=\"Zip/Postal Code\"]/following::input[1]");
   private By country=By.xpath("//div[text()=\"-- Select --\"]");
   private By countrylist=By.xpath("//span[text()=\"India\"]");
   private By homenum=By.xpath("//label[text()=\"Home\"]/following::input[1]");
   private By mobile=By.xpath("//label[text()=\"Mobile\"]/following::input[1]");
   private By work=By.xpath("//label[text()=\"Work\"]/following::input[1]");
    //Email
    private By wemail= By.xpath("//label[text()=\"Work Email\"]/following::input[1]");
    private By oemail=By.xpath("//label[text()=\"Other Email\"]/following::input[1]");

    //Emergency contact
    private By assignedecontact=By.xpath("//h6[text()=\"Assigned Emergency Contacts\"]");
    private By econtact= By.xpath("//a [text()=\"Emergency Contacts\"]");
    private By eaddbtn=By.xpath("(//i [@class=\"oxd-icon bi-plus oxd-button-icon\"])[1]");
    private By emergemcynane=By.xpath("//label[text()=\"Name\"]/following::input[1]");
    private By eRelationship=By.xpath("//label[text()=\"Relationship\"]/following::input[1]");
    private By hometelephone=By.xpath("//label[text()=\"Home Telephone\"]/following::input[1]");
    private By emobile=By.xpath("//label[text()=\"Mobile\"]/following::input[1]");
    private By worktelephone=By.xpath("//label[text()=\"Work Telephone\"]/following::input[1]");
    private By esavebtn=By.xpath("//div[@class=\"oxd-form-actions\"]//button[2]");


    public String addmyinfo(){
        log.info("Navigating to My Info section");
        waithelpers.checkVisibility(driver,myinfo);
        driver.findElement(myinfo).click();
        waithelpers.checkVisibility(driver,personaldetails);
        log.info("Personal Details page loaded");

        log.debug("Entering First Name");
        driver.findElement(fristname).click();
        driver.findElement(fristname).sendKeys(Keys.CONTROL+"a");
        driver.findElement(fristname).sendKeys(Keys.DELETE);
        driver.findElement(fristname).sendKeys(PropertyReader.readkey("fname"));

        log.debug("Entering Middle Name");
        driver.findElement(middlename).click();
        driver.findElement(middlename).sendKeys(Keys.CONTROL+"a");
        driver.findElement(middlename).sendKeys(Keys.DELETE);
        driver.findElement(middlename).sendKeys(PropertyReader.readkey("mname"));

        log.debug("Entering Last Name");
        driver.findElement(lastname).click();
        driver.findElement(lastname).sendKeys(Keys.CONTROL+"a");
        driver.findElement(lastname).sendKeys(Keys.DELETE);
        driver.findElement(lastname).sendKeys(PropertyReader.readkey("lname"));

        log.debug("Entering Employee ID");
        driver.findElement(empid).sendKeys(Keys.CONTROL+"a");
        driver.findElement(empid).sendKeys(Keys.DELETE);
        driver.findElement(empid).sendKeys(PropertyReader.readkey("empid"));

        log.debug("Entering Other ID");
        driver.findElement(otherid).sendKeys(Keys.CONTROL+"a");
        driver.findElement(otherid).sendKeys(Keys.DELETE);
        driver.findElement(otherid).sendKeys(PropertyReader.readkey("otherid"));

        log.debug("Entering Driver's Licence Number");
        driver.findElement(drivelicence).sendKeys(Keys.CONTROL+"a");
        driver.findElement(drivelicence).sendKeys(Keys.DELETE);
        driver.findElement(drivelicence).sendKeys(PropertyReader.readkey("dlicence"));

        log.debug("Entering Licence Expiry Date");
        driver.findElement(licence_exp).sendKeys(Keys.CONTROL+"a");
        driver.findElement(licence_exp).sendKeys(Keys.DELETE);
        driver.findElement(licence_exp).sendKeys(PropertyReader.readkey("licence"));

        log.info("Selecting Nationality");
        driver.findElement(nation).click();
        waithelpers.checkVisibility(driver,nationlist);
        driver.findElement(nationlist).click();

        log.info("Selecting Marital Status");
        driver.findElement(marragestatus).click();
        waithelpers.checkVisibility(driver,marstatus);
        driver.findElement(marstatus).click();

        log.debug("Entering Date of Birth");
        driver.findElement(dob).sendKeys(Keys.CONTROL+"a");
        driver.findElement(dob).sendKeys(Keys.DELETE);
        driver.findElement(dob).sendKeys(PropertyReader.readkey("dob"));

        log.debug("Selecting Gender: Male");
        driver.findElement(gender).click();

        log.info("Clicking Save button");
        driver.findElement(savebtn).click();
        waithelpers.checkVisibility(driver,sucesstext);
        String smesg=driver.findElement(sucesstext).getText();
        log.info("Save response: {}", smesg);
        return smesg;
    }

    public void contact_details(){
        waithelpers.checkVisibility(driver,myinfo);
        driver.findElement(myinfo).click();
        waithelpers.checkVisibility(driver,contactdetails);
        driver.findElement(contactdetails).click();
        waithelpers.checkVisibility(driver,address);
        driver.findElement(street1).click();
        driver.findElement(street1).sendKeys(PropertyReader.readkey("Street1"));
        driver.findElement(street2).sendKeys(PropertyReader.readkey("Street2"));
        driver.findElement(city).sendKeys(PropertyReader.readkey("city"));
        driver.findElement(state).sendKeys(PropertyReader.readkey("State"));
        driver.findElement(zipcode).sendKeys(PropertyReader.readkey("zipcode"));
        driver.findElement(country).click();
        waithelpers.checkVisibility(driver,countrylist);
        driver.findElement(countrylist).click();
        driver.findElement(homenum).sendKeys(PropertyReader.readkey("homenum"));
        driver.findElement(mobile).sendKeys(PropertyReader.readkey("mobile"));
        driver.findElement(work).sendKeys(PropertyReader.readkey("work"));
        driver.findElement(wemail).sendKeys(PropertyReader.readkey("webmail"));
        driver.findElement(oemail).sendKeys(PropertyReader.readkey("oemail"));
        driver.findElement(savebtn).click();
    }

    public void emergencyDetails() {
        waithelpers.checkVisibility(driver,myinfo);
        driver.findElement(myinfo).click();
     waithelpers.checkVisibility(driver,econtact);
     driver.findElement(econtact).click();
     waithelpers.checkVisibility(driver, assignedecontact);
     driver.findElement(eaddbtn).click();
     driver.findElement(emergemcynane).sendKeys(PropertyReader.readkey("ename"));
     driver.findElement(eRelationship).sendKeys(PropertyReader.readkey("relationship"));
     driver.findElement(hometelephone).sendKeys(PropertyReader.readkey("telephone"));
     driver.findElement(emobile).sendKeys(PropertyReader.readkey("emobile"));
     driver.findElement(worktelephone).sendKeys(PropertyReader.readkey("worktelephone"));
     driver.findElement(esavebtn).click();
    }
}

