import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.nio.file.WatchEvent;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HeadFirst {
    private WebDriver driver;
    private Book headFirst;
    static final String APP_URL = "https://www.amazon.com/Head-First-Java-Kathy-Sierra/dp/0596009208/ref=sr_1_2?dchild=1&keywords=Java&qid=1610356790&s=books&sr=1-2";

    HeadFirst(WebDriver driver)
    {
        this.driver = driver;
    }

    public void open()
    {
        try {
            driver.get(APP_URL);
            Thread.sleep(1000);
            createBook();
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
        if(driver.getTitle().equals("Head First Java, 2nd Edition: Sierra, Kathy, Bates, Bert: 8601404235726: Amazon.com: Books"))
            return true;
        else
            return false;
    }

    private void createBook()
    {
        List<WebElement> p = driver.findElements(By.xpath(".//span[@class='a-size-base mediaTab_subtitle']"));
        float price = Float.parseFloat(p.get(0).getText().substring(1,p.get(0).getText().indexOf(" ")));
        String name = driver.findElement(By.xpath(".//span[@id='productTitle']")).getText();
        String author = driver.findElement(By.xpath(".//div[@id='bylineInfo_feature_div']")).getText()
                .replace("(Author)","").replace(",","");
        String[] fullAuthor = author.split("\n");
        author = fullAuthor[0] + " ";
        for(int i = 1;i < fullAuthor.length;i++)
        {
            author+=fullAuthor[i];
            if(i+1 < fullAuthor.length)
            {
                author+="and ";
            }
        }
        author = author.trim();
        if(driver.findElements(By.xpath(".//i[@class='a-icon a-icon-addon p13n-best-seller-badge']"))
                .size()!=0)
        {
            System.out.println("This is a bestseller");
        }
        else
        {
            System.out.println("This is not a bestseller");
        }
        headFirst = new Book(name,author,price);
    }

    public Book getBook()
    {
        return headFirst;
    }

    public void displayBook()
    {
        System.out.println(headFirst.toString());
    }
}
