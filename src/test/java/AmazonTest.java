import junit.framework.TestCase;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class AmazonTest extends TestCase {

    @Test
    public void testEmAll()
    {
        Path path = Paths.get("Components/chromedriver/chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", path.toString());
        WebDriver driver = new ChromeDriver();
        List<Book> books;
        Book headFirst;

        Amazon a = new Amazon(driver);
        a.open();
        a.goToBooks();

        Books b = new Books(driver);
        books = b.getBooks();
        System.out.println("The book from the 'Books' class method : ");
        b.displayBook(2);

        HeadFirst hf = new HeadFirst(driver);
        hf.open();
        assertTrue(hf.atPage());
        hf.displayBook();
        headFirst = hf.getBook();
        hf.close();
        if(books.contains(headFirst))
            System.out.println("The book 'Head first...' is in this list. Their hash codes are : "
            + headFirst.hashCode() +" - "+ books.get(books.indexOf(headFirst)).hashCode());
        else
            System.out.println("He isn't here, is he?");
        System.out.println(headFirst.equals(books.get(2)));
    }

    @Test
    public void testAmazonBooks()
    {
        Path path = Paths.get("Components/chromedriver/chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", path.toString());
        WebDriver driver = new ChromeDriver();
        Amazon a = new Amazon(driver);
        a.open();
        assertTrue(a.atPage());
        a.goToBooks();
        Books b = new Books(driver);
        assertTrue(b.atPage());
        b.displayBooks();
        b.close();
    }

    @Test
    public void testAmazonBook()
    {
        Path path = Paths.get("Components/chromedriver/chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", path.toString());
        WebDriver driver = new ChromeDriver();
        List<Book> books;
        Amazon a = new Amazon(driver);
        a.open();
        a.goToBooks();
        Books b = new Books(driver);
        System.out.println("The book from the 'books' variable : ");
        books = b.getBooks();
        System.out.println(books.get(2).toString());
        System.out.println("The book from the 'Books' class method : ");
        b.displayBook(2);
        b.close();
    }

    @Test
    public void testHeadFirst()
    {
        Path path = Paths.get("Components/chromedriver/chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", path.toString());
        WebDriver driver = new ChromeDriver();
        HeadFirst hf = new HeadFirst(driver);
        hf.open();
        assertTrue(hf.atPage());
        hf.displayBook();
        hf.close();
    }
}