package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\Browser drivers\\chromedriver.exe");

        WebDriver driver = null;

        try {
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.get("https://www.google.com");

            Thread.sleep(5000);

        } catch (Exception e) {
            logger.log(Level.SEVERE, "An error occurred while running the test", e);
        } finally {
            if (driver != null) {
                driver.quit();
                logger.info("Browser closed successfully.");
            }
        }
    }
}
