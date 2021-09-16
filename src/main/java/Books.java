import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.List;

public class Books {
    private WebDriver driver;
    private List<WebElement> books;

    Books(WebDriver driver)
    {
        this.driver = driver;
        books = driver.findElements(By.xpath("//div[@class='s-main-slot s-result-list s-search-results sg-row']" +
                "/div[@class='s-result-item s-asin sg-col-0-of-12 sg-col-16-of-20 sg-col sg-col-12-of-16']"));
    }

    public List<WebElement> getBooks()
    {
        return books;
    }

    public void close()
    {
        driver.close();
    }

    public void displayBooks()
    {
        for(WebElement wb : books)
        {
            System.out.println(wb.getText());
            System.out.println("-------------------------------------");
        }
    }

    public void displayBook(int index)
    {
        try {
            System.out.println(books.get(index).getText());
        }
        catch(IndexOutOfBoundsException e)
        {
            System.out.println("You entered too big value for index. The maximum number of elements is " + books.size());
        }
    }

    public void findBook()
    {
        String[] info;
        String bookName = "Head First Java, 2nd Edition";
        int counter = 0;
        for(int i = 0;i < books.size();i++)
        {
            info = books.get(i).getText().split("\\r?\\n");
            if(info[0].contains(bookName)) {
                System.out.println("The book '"+bookName+"' is in this list");
                return;
            }
        }
        System.out.println("The book 'Head First Java, 2nd Edition' is not in this list");
    }

    public boolean atPage()
    {
        if(driver.getTitle().equals("Amazon.com : Java"))
            return true;
        else
            return false;
    }

}
