package tests;

import dataProviders.loginDp;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import setup.BaseClass;

public class loginPage extends BaseClass {

    @Test(dataProvider = "validUser", dataProviderClass = loginDp.class)
    public void testUsername(String username, boolean valid, String password) throws InterruptedException {
        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).submit();
        Thread.sleep(2000);
        if (valid) {
            String title = driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/span")).getText();
            Assert.assertEquals(title, "Products");
        } else if (username == "") {
            String error = driver.findElement(By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]/h3")).getText();
            Assert.assertEquals(error, "Epic sadface: Username is required");
        } else {
            String error = driver.findElement(By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]/h3")).getText();
            Assert.assertEquals(error, "Epic sadface: Username and password do not match any user in this service");
        }
    }


    @Test(dataProvider = "validUser", dataProviderClass = loginDp.class)
    public void testPassword(String username, boolean valid, String password) throws InterruptedException {
        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).submit();
        Thread.sleep(2000);
        if (valid) {
            String title = driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/span")).getText();
            Assert.assertEquals(title, "Products");
        } else if (password == "") {
            String error = driver.findElement(By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]/h3")).getText();
            Assert.assertEquals(error, "Epic sadface: Password is required");
        } else {
            String error = driver.findElement(By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]/h3")).getText();
            Assert.assertEquals(error, "Epic sadface: Username and password do not match any user in this service");
        }
    }
}
