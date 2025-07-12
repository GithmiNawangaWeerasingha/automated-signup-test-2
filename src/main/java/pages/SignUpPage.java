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

    public void clickSignin(){
        By signInLocator = By.xpath("//a[.//span[text()='Sign in']]");
        WebElement signInLink = wait.until(ExpectedConditions.elementToBeClickable(signInLocator));
        signInLink.click();
    }

    public void clickSignupText(){
        System.out.println("Clicking on 'Sign up' link...");

        By signUpLocator = By.xpath("//div[@class='popup_bottom_text']//a[contains(text(),'Sign up')]");

        WebElement signUpLink = wait.until(ExpectedConditions.elementToBeClickable(signUpLocator));
        signUpLink.click();
    }



    public void clickSignUpWithEmail() {
        By signupWithEmailLocator = By.id("continue_with_email_signup");

        WebElement signupWithEmailLink = wait.until(ExpectedConditions.elementToBeClickable(signupWithEmailLocator));
        signupWithEmailLink.click();
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
