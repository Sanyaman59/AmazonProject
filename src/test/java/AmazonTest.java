import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AmazonTest{

    WebDriver driver;
    static final String HOST_URL = "http://localhost:4444/wd/hub";

    @Test
    public void testEmAll()
    {
        ChromeOptions options = new ChromeOptions();
        //DesiredCapabilities dc = DesiredCapabilities.chrome();
//        if(Platform.getCurrent()==Platform.WIN10)
//            dc.setPlatform(Platform.WINDOWS);
//        else
//            dc.setPlatform(Platform.getCurrent());
        try {
            driver = new RemoteWebDriver(new URL(HOST_URL), options);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
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
    }



    @Test
    public void testAmazonBooks()
    {
        ChromeOptions options = new ChromeOptions();
        //DesiredCapabilities dc = DesiredCapabilities.chrome();
//        if(Platform.getCurrent()==Platform.WIN10)
//            dc.setPlatform(Platform.WINDOWS);
//        else
//            dc.setPlatform(Platform.getCurrent());
        try {
            driver = new RemoteWebDriver(new URL(HOST_URL), options);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Amazon a = new Amazon(driver);
        a.open();
        a.goToBooks();
        Books b = new Books(driver);
        b.displayBooks();
    }



    @Test
    public void testAmazonBook()
    {
        ChromeOptions options = new ChromeOptions();
        //DesiredCapabilities dc = DesiredCapabilities.chrome();
//        if(Platform.getCurrent()==Platform.WIN10)
//            dc.setPlatform(Platform.WINDOWS);
//        else
//            dc.setPlatform(Platform.getCurrent());
        try {
            driver = new RemoteWebDriver(new URL(HOST_URL), options);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
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
        ChromeOptions options = new ChromeOptions();
        //DesiredCapabilities dc = DesiredCapabilities.chrome();
//        if(Platform.getCurrent()==Platform.WIN10)
//            dc.setPlatform(Platform.WINDOWS);
////        else
////            dc.setPlatform(Platform.getCurrent());
        try {
            driver = new RemoteWebDriver(new URL(HOST_URL), options);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        HeadFirst hf = new HeadFirst(driver);
        hf.open();
        Assert.assertTrue(hf.atPage());
        hf.displayBook();
    }

    @AfterMethod
    public void tearDown()
    {
        if(driver != null)
        {
            driver.quit();
        }
    }
}