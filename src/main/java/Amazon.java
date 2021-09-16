import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
public class Amazon {
    private WebDriver driver;

    Amazon(WebDriver driver)
    {
        this.driver = driver;
    }

    public void open()
    {
        try {
            driver.get("https://www.amazon.com");
            Thread.sleep(1000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void close()
    {
        driver.close();
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
        //book.click();

        //Actions actions = new Actions(driver);
        try {
            book.click();
            Thread.sleep(2000);
            dropdownElement.selectByVisibleText("Books");
            driver.findElement(By.xpath("//input[@name='field-keywords']")).sendKeys("Java",Keys.ENTER);
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
