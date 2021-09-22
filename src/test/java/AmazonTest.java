import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class AmazonTest{

    @Test
    public void testEmAll()
    {
        DesiredCapabilities dc = DesiredCapabilities.chrome();
        WebDriver driver = null;
        try {
            driver = new RemoteWebDriver(new URL("http://192.168.0.108:4444/wd/hub"), dc);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        driver.manage().window().maximize();
        List<Book> books;
        Book headFirst;

        Amazon a = new Amazon(driver);
        a.open();
        Assert.assertTrue(a.atPage());
        a.goToBooks();

        Books b = new Books(driver);
        books = b.getBooks();
        System.out.println("The book from the 'Books' class method : ");
        b.displayBook(2);

        HeadFirst hf = new HeadFirst(driver);
        hf.open();
        Assert.assertTrue(hf.atPage());
        hf.displayBook();
        headFirst = hf.getBook();
        if(books.contains(headFirst)) {
            Assert.assertEquals(headFirst.hashCode(), books.get(books.indexOf(headFirst)).hashCode());
            System.out.println("The book 'Head first...' is in this list. Their hash codes are : "
                    + headFirst.hashCode() + " : " + books.get(books.indexOf(headFirst)).hashCode());
        }
        else
            System.out.println("He isn't here, is he?");
        System.out.println(headFirst.equals(books.get(2)));
        driver.quit();
    }

    @Test
    public void testAmazonBooks()
    {
        DesiredCapabilities dc = DesiredCapabilities.chrome();
        WebDriver driver = null;
        try {
            driver = new RemoteWebDriver(new URL("http://192.168.0.108:4444/wd/hub"), dc);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        driver.manage().window().maximize();
        Amazon a = new Amazon(driver);
        a.open();
        a.goToBooks();
        Books b = new Books(driver);
        b.displayBooks();
        driver.quit();
    }

    @Test
    public void testAmazonBook()
    {
        DesiredCapabilities dc = DesiredCapabilities.chrome();
        WebDriver driver = null;
        try {
            driver = new RemoteWebDriver(new URL("http://192.168.0.108:4444/wd/hub"), dc);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        driver.manage().window().maximize();
        List<Book> books;
        Amazon a = new Amazon(driver);
        a.open();
        a.goToBooks();
        Books b = new Books(driver);
        System.out.println("The book from the 'Books' class method : ");
        b.displayBook(2);;
        System.out.println("The book from the 'books' variable : ");
        books = b.getBooks();
        System.out.println(books.get(2).toString());
        driver.quit();
    }

    @Test
    public void testHeadFirst()
    {
        DesiredCapabilities dc = DesiredCapabilities.chrome();
        WebDriver driver = null;
        try {
            driver = new RemoteWebDriver(new URL("http://192.168.0.108:4444/wd/hub"), dc);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        driver.manage().window().maximize();
        HeadFirst hf = new HeadFirst(driver);
        hf.open();
        Assert.assertTrue(hf.atPage());
        hf.displayBook();
        driver.quit();
    }
}