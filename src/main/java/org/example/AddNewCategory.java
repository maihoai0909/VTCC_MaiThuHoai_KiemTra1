package org.example;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import org.testng.Assert;

public class AddNewCategory extends BaseTest {
    @Test
    public void AddNew() throws Exception {

        //Mở trang cms.anhtester.com
        driver.get("https://cms.anhtester.com/login");
        Thread.sleep(5000);

        // Login vào trang cms
        WebElement password = driver.findElement(By.id("password"));
        password.clear();
        driver.findElement(By.id("email")).sendKeys("admin@example.com");
        Thread.sleep(1000);
        driver.findElement(By.id("password")).sendKeys("123456");
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/div/div/div/div/div/div/div/div/form/button")).click();

        // Add new category
        driver.findElement(By.xpath("//*[@id=\"main-menu\"]/li[2]/a/span[1]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"main-menu\"]/li[2]/ul/li[8]/a/span")).click();
        Thread.sleep(2000);
        driver.findElement(By.linkText("Add New category")).click();
        Thread.sleep(3000);
        // Name
        driver.findElement(By.id("name")).sendKeys("Women Clothing & Model");
        Thread.sleep(1000);
        // Parent Category(Chọn ---- T-shirt)
        Select parent_category = new Select(driver.findElement(By.name("parent_id")));
        parent_category.selectByVisibleText("---- T-shirt");
        Thread.sleep(2000);
        //Ordering Number
        driver.findElement(By.id("order_level")).sendKeys("65");
        Thread.sleep(2000);
        // Type (Chọn Digital)
        Select select = new Select(driver.findElement(By.name("digital")));
        select.selectByVisibleText("Digital");
        Thread.sleep(2000);
        //Meta Title
        driver.findElement(By.cssSelector("[placeholder = 'Meta Title']")).sendKeys("test");
        Thread.sleep(1000);

        //Meta Description
        driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/div[1]/div/div/div/div[2]/form/div[8]/div/textarea")).sendKeys("test");
        Thread.sleep(1000);

        //Filtering Attributes (Chọn Size)
        Select fill_attr = new Select(driver.findElement(By.name("filtering_attributes[]")));
        fill_attr.selectByVisibleText("Size");
        Thread.sleep(1000);

        //Chọn Save
        driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/div[1]/div/div/div/div[2]/form/div[10]/button")).click();
        Thread.sleep(3000);
        // Search category vừa tạo(Women Clothing & Model) để verify kết quả
        WebElement src_category = driver.findElement(By.id("search"));
        src_category.sendKeys("Women Clothing & Model");
        Actions action = new Actions(driver);
        action.sendKeys(Keys.RETURN);
        action.perform();
        Thread.sleep(4000);
        WebElement searchresult = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]/div[1]/div[2]/div[2]/table/tbody/tr/td[2]"));
        Assert.assertEquals(searchresult.getText(),"Women Clothing & Model","FAILED. Category name not match");
    }
}

