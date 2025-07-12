package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SignUpPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public SignUpPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void open() {
        driver.get("https://cog-stg.incubatelabs.com");
    }

    public void clickSignUp() {

        WebElement signUpBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(text(), 'Sign up')]")));
        signUpBtn.click();
    }

    public void clickSignUpWithEmail() {
        WebElement signUpEmailBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(text(), 'Sign up with Email')]")));
        signUpEmailBtn.click();
    }

    public void fillForm(String firstName, String lastName, String email, String phone, String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("firstName"))).sendKeys(firstName);
        driver.findElement(By.name("lastName")).sendKeys(lastName);
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("phone")).sendKeys(phone);
        driver.findElement(By.name("password")).sendKeys(password);
    }

    public boolean submitAndVerify() {
        driver.findElement(By.xpath("//button[contains(text(),'Create Account')]")).click();
        try {
            wait.until(ExpectedConditions.urlContains("/verify"));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
