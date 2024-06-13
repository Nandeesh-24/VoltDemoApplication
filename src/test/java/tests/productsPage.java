package tests;

import dataProviders.productsDp;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import setup.BaseClass;
import utils.Utils;

import java.util.List;

public class productsPage extends BaseClass {

    @BeforeMethod
    public void login() {
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).submit();
    }

    @Test
    public void testAddTOCartButton(){
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        try {
            String cartCount = driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a/span")).getText();
            Assert.assertEquals(cartCount, "1");
        }catch (NoSuchElementException e){
            Assert.fail("Cart Count missing");
        }
    }

    @Test(dataProvider = "addProduct", dataProviderClass = productsDp.class)
    public void testRemoveButton(List<String> items){
        for (String item : items){
            driver.findElement(By.id("add-to-cart-sauce-labs-"+item)).click();
        }
        try {
            String cartCount = driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a/span")).getText();
            Assert.assertEquals(Integer.parseInt(cartCount), items.size());
        }catch (NoSuchElementException e){
            Assert.fail("Cart Count missing");
        }
        driver.findElement(By.id("remove-sauce-labs-backpack")).click();
        try {
            String cartCount = driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a/span")).getText();
            Assert.assertEquals(Integer.parseInt(cartCount), items.size()-1);
        }catch (NoSuchElementException e){
            if (!(items.size()==1))
                Assert.fail("Invalid cart count");
        }
    }
}
