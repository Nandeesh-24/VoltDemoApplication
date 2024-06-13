package tests;

import dataProviders.CheckoutDp;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import setup.BaseClass;
import utils.Utils;

public class checkoutPage extends BaseClass {

    @BeforeMethod
    public void loginAndCart() {
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).submit();

        Utils.wait(driver, By.id("add-to-cart-sauce-labs-backpack")).click();
        try {
            String cartCount = driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a/span")).getText();
            Assert.assertEquals(cartCount, "1");
        } catch (NoSuchElementException e) {
            Assert.fail("Cart Count missing");
        }
        driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a")).click();
        try {
            Utils.wait(driver, By.id("checkout")).click();
        }catch (NoSuchElementException e){
            Assert.fail("Checkout button is not found in Cart page");
        }
    }

    @Test(dataProvider = "checkoutInfo", dataProviderClass = CheckoutDp.class)
    public void testCheckoutForm(String firtName, String lastName, String postalCode) throws InterruptedException {

        try{
            String checkoutPageTitle = Utils.wait(driver,By.xpath("//*[@id=\"header_container\"]/div[2]/span")).getText();
            Assert.assertEquals(checkoutPageTitle,"Checkout: Your Information");
        }catch (NoSuchElementException e){
            Assert.fail("Checkout page is not found");
        }

        System.out.println(firtName);

        driver.findElement(By.id("first-name")).sendKeys(firtName);
        driver.findElement(By.id("last-name")).sendKeys(lastName);
        driver.findElement(By.id("postal-code")).sendKeys(postalCode);

        driver.findElement(By.id("continue")).click();
        if (firtName == "" || lastName == "" || postalCode == "") {
            String error = driver.findElement(By.xpath("//*[@id=\"checkout_info_container\"]/div/form/div[1]/div[4]/h3")).getText();
            if (firtName == "")
                Assert.assertEquals(error, "Error: First Name is required");
            else if (lastName == "")
                Assert.assertEquals(error, "Error: Last Name is required");
            else if (postalCode == "")
                Assert.assertEquals(error, "Error: Postal Code is required");
        }else {

            try {
                String checkoutOveriewTitle = Utils.wait(driver, By.xpath("//*[@id=\"header_container\"]/div[2]/span")).getText();
                Assert.assertEquals(checkoutOveriewTitle, "Checkout: Overview");
            } catch (NoSuchElementException e) {
                Assert.fail("Checkout Overview page is not found");
            }
        }

    }
}
