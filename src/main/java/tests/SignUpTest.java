package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.SignUpPage;
import utils.ValidationUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SignUpTest {

    private static final Logger logger = Logger.getLogger(SignUpTest.class.getName());

    public static void main(String[] args) {
        Properties testData = new Properties();

        // Load test data
        try (FileInputStream input = new FileInputStream("src/main/resources/testdata.properties")) {
            testData.load(input);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Failed to load test data: {0}", e.getMessage());
            return;
        }

        // Set path to ChromeDriver
        WebDriver driver = null;

        driver = new ChromeDriver();
        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.manage().window().maximize();

            SignUpPage page = new SignUpPage(driver);
            page.open();
            page.clickSignin();
            page.clickSignupText();
            page.clickSignUpWithEmail();

            String email = testData.getProperty("email");
            String phone = testData.getProperty("phone");
            String password = testData.getProperty("password");

            if (!ValidationUtils.isValidEmail(email)) {
                logger.warning("Invalid email format!");
                return;
            }
            if (!ValidationUtils.isValidPhone(phone)) {
                logger.warning("Invalid phone format!");
                return;
            }
            if (!ValidationUtils.isStrongPassword(password)) {
                logger.warning("Weak password!");
                return;
            }

            page.fillForm(
                    testData.getProperty("first_name"),
                    testData.getProperty("last_name"),
                    email, phone, password
            );

            boolean success = page.submitAndVerify();
            if (success) {
                logger.info("Sign-up successful!");
            } else {
                logger.warning("Sign-up FAILED!");
            }

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Exception occurred during sign-up test", e);
        } finally {
            driver.quit();
            logger.info("Test completed. Browser closed.");
        }
    }
}
