package TestOtomasyon.tests;

import TestOtomasyon.drivers.Driver;
import TestOtomasyon.utils.PropertyManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import javax.swing.text.html.CSS;
import java.io.IOException;
import java.net.MalformedURLException;
import java.time.Duration;
import java.time.Instant;
import java.util.List;

public class xpathHomework {

    public static WebDriver driver;
    Driver webDriver = new Driver();
    PropertyManager propertyManager= new PropertyManager();
    String url =propertyManager.getProperty("APP_URL");



    @BeforeMethod(alwaysRun = true)
    public void before() throws MalformedURLException {
        driver = webDriver.initializeDriver();
        driver.get(url);
    }

    @Test
    public void openDemoQaTest() {
        String pageTitle = driver.getTitle();
        Assert.assertEquals(pageTitle,"DEMOQA");
    }

    @Test
    public void clickMeButtonQaTest(){

        //BUTTONS BUTONU TIKLANIR

        WebElement buttons = driver.findElement(By.xpath("//span[normalize-space()='Buttons']"));
        buttons.click();

        WebElement clickMe = driver.findElement(By.xpath("//button[text()='Click Me']"));
        clickMe.click();

        //EKRANDAKİ TEXT MESAJI OKUNUR VE YAZDIRILIR

        WebElement textMessage = driver.findElement(By.xpath("/html//p[@id='dynamicClickMessage']"));
        System.out.println("Ekranda Gorulen Text Mesaji: "+ textMessage.getText());

    }

    @Test
    public void webTablesAdd(){

        //WEBTABLES SEKMESİ TIKLANILIR
        WebElement webTables = driver.findElement(By.xpath("//*[@id='item-3']//*[text()='Web Tables']"));
        webTables.click();

        //ADD BUTONUNA TIKLANILIR
        WebElement addButton = driver.findElement(By.xpath("//button[@id='addNewRecordButton']"));
        addButton.click();

        WebElement firstName= driver.findElement(By.xpath("//input[@id='firstName']"));
        WebElement lastName= driver.findElement(By.xpath("//input[@id='lastName']"));
        WebElement email= driver.findElement(By.xpath("//input[@id='userEmail']"));
        WebElement age= driver.findElement(By.xpath("//input[@id='age']"));
        WebElement salary= driver.findElement(By.xpath("//input[@id='salary']"));
        WebElement department= driver.findElement(By.xpath("//input[@id='department']"));
        WebElement btnSubmit= driver.findElement(By.xpath("//button[@id='submit']"));

        firstName.sendKeys("Özge");
        lastName.sendKeys("Şen Kaya");
        email.sendKeys("ozgeesenn1995@gmail.com");
        age.sendKeys("29");
        salary.sendKeys("234556");
        department.sendKeys("QA Team");
        btnSubmit.click();

        //DUZENLE BUTONUNA TIKLANILIR VE DUZENLEME YAPILIR
        findEntry("ozgeesenn1995@gmail.com");

        waitAndFillInputForElement( By.xpath("//input[@id='firstName']"),"Ezgi");
        waitAndFillInputForElement( By.xpath("//input[@id='lastName']"),"Kaya");
        driver.findElement(By.xpath("//button[@id='submit']")).click();

    }

    public void findEntry(String text) {

        List<WebElement> tableElements = driver.findElements(By.xpath("//div[@class='rt-tbody']//div[@role='row']/div[4]"));


        for(int i=0; i< tableElements.size();i++){

            if (tableElements.get(i).getText().equalsIgnoreCase(text)){
                driver.findElement(By.xpath("//span[@id='edit-record-" + (i + 1) + "']")).click();
                break;
            }
        }
    }

    protected void waitAndFillInputForElement(By by, String text) {
        WebElement element = driver.findElement(by);
        element.clear();
        element.sendKeys(text);
    }


    @AfterMethod(alwaysRun = true)
    public void  after(){
        webDriver.quitDriver();
    }

}


