import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
public class Amazon {
    private WebDriver driver;
    static final String APP_URL = "https://www.amazon.com";

    Amazon(WebDriver driver)
    {
        this.driver = driver;
    }

    public void open()
    {
        try {
            driver.get(APP_URL);
            Thread.sleep(1000);
            System.out.println("Half past ten");
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void quit()
    {
        driver.quit();
    }

    public boolean atPage()
    {
        if(driver.getTitle().equals("Amazon.com. Spend less. Smile more."))
            return true;
        else
            return false;
    }


    public void goToBooks()
    {
        WebElement book = driver.findElement(By.id("nav-search-dropdown-card"));
        WebElement select = driver.findElement(By.name("url"));
        Select dropdownElement = new Select(select);
        try {
            book.click();
            Thread.sleep(2000);
            dropdownElement.selectByIndex(5);
            driver.findElement(By.xpath("//input[@name='field-keywords']")).sendKeys("Java",Keys.ENTER);
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
