package com.UITest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SauceLabTest {
     WebDriver driver;

    @Test
    public void test1() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Users/habib/Desktop/chromedriver");
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        driver.manage().window().maximize();
        Thread.sleep(1000);

        driver.findElement(By.xpath("//*[@id='user-name']")).sendKeys("standard_user");
        driver.findElement(By.xpath("//*[@id='password']")).sendKeys("secret_sauce");
        driver.findElement(By.xpath("//*[@id='login-button']")).click();
        Thread.sleep(1000);
        //	List<WebElement> listItem = driver.findElements(By.xpath("//*[@class='inventory_item_price']"));

        List<String> prices = new ArrayList<String>();
        List<WebElement> listItem = driver.findElements(By.xpath("//*[contains(@class, 'inventory_item_price')]"));
        for(int i = 0; i < listItem.size(); i++){
            prices.add(listItem.get(i).getText());
        }
        Collections.sort(prices);
        System.out.println("Total size: " + prices.size());

        Assert.assertEquals(6, prices.size());
        Thread.sleep(1000);

        // adding 3 items int the cart
        driver.findElement(By.xpath("(//*[contains(@class, 'btn_primary btn_inventory')])[1]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("(//*[contains(@class, 'btn_primary btn_inventory')])[2]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("(//*[contains(@class, 'btn_primary btn_inventory')])[3]")).click();
        Thread.sleep(1000);

        String cartNumber = driver.findElement(By.xpath("//*[@class='fa-layers-counter shopping_cart_badge']")).getText();

        System.out.println(cartNumber);

        Assert.assertEquals(cartNumber, "3", "msessage passed");

        System.out.println("one item removed");

        driver.findElement(By.xpath("(//*[@class='btn_secondary btn_inventory'])[1]")).click();

        String cartNumber1 = driver.findElement(By.xpath("//*[@class='fa-layers-counter shopping_cart_badge']")).getText();
        System.out.println(" remove second time " + cartNumber);

        //Assert.assertEquals(cartNumber,"2","remove 2nd times");
        Thread.sleep(1000);

        driver.findElement(By.xpath("(//*[@class='btn_primary btn_inventory'])[4]")).click();

        String cartNumber2 = driver.findElement(By.xpath("//*[@class='fa-layers-counter shopping_cart_badge']")).getText();
        System.out.println(" remove Third time " + cartNumber2);


        driver.findElement(By.xpath("//*[@class='fa-layers-counter shopping_cart_badge']")).click();

        driver.findElement(By.xpath("//*[@class='btn_action checkout_button']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id='first-name']")).sendKeys("Robert");
        driver.findElement(By.xpath("//*[@id='last-name']")).sendKeys("khan");
        driver.findElement(By.xpath("//*[@id='postal-code']")).sendKeys("10001");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@type='submit']")).click();
        Thread.sleep(1000);

        String totalPrice = driver.findElement(By.xpath("//*[@class='summary_total_label']")).getText();
        System.out.println("Total price is " + totalPrice);
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@class='btn_action cart_button']")).click();

        String finishTest = driver.findElement(By.xpath("//*[@class='subheader']")).getText();
        Thread.sleep(1000);
        System.out.println(finishTest);
    }
}
