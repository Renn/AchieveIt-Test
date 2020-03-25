import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.stream.Stream;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Temp {

  JavascriptExecutor js;
  private WebDriver driver;
  private Map<String, Object> vars;

  @AfterAll
  static void afterAll() {
    Driver.quit();
  }

  static Stream<Arguments> loginInfoProvider() {
    final String correctUsername = "b6703879-e1e2-499c-8ffe-d8b29f71f156";
    final String correctPassword = "123456";
    final String wrongUsername = "b6703879-e1e2-1234-8ffe-d8b29f71f156";
    final String wrongPassword = "123456789";
    final String NO_ACCOUNT = "no account!";
    final String PASSWORD_WRONG = "password wrong!";
    int length = 11000;
    Random random = new Random();
    int[] usernameArray = random.ints(length, 33, 127).toArray();
    int[] passwordArray = random.ints(length, 33, 127).toArray();
    String longUsername = new String(usernameArray, 0, usernameArray.length);
    String longPassword = new String(passwordArray, 0, passwordArray.length);
    return Stream.of(
        Arguments.of(correctUsername, correctPassword, true, null),
        // Wrong
        Arguments.of(wrongUsername, correctPassword, false, NO_ACCOUNT),
        Arguments.of(correctUsername, wrongPassword, false, PASSWORD_WRONG),
        Arguments.of(wrongUsername, wrongPassword, false, NO_ACCOUNT));
    // Long
//        Arguments.of(longUsername, correctPassword, false, NO_ACCOUNT),
//        Arguments.of(correctUsername, longPassword, false, PASSWORD_WRONG),
//        Arguments.of(longUsername, longPassword, false, NO_ACCOUNT));
  }

  @BeforeEach
  public void setUp() {
    driver = Driver.getDriver();
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
  }

  @AfterEach
  public void tearDown() {
  }

  @ParameterizedTest
  @MethodSource("loginInfoProvider")
  public void login(String username, String password, boolean success, String errorMessage) {
    driver.get("http://116.62.181.135:8080/achieveit/#/login");
    //Must refresh to make previous result invisible
    driver.navigate().refresh();
    // Input username and password and click login button
    WebElement usernameInput = driver.findElement(By.name("username"));
    usernameInput.clear();
    usernameInput.sendKeys(username);
    WebElement passwordInput = driver.findElement(By.name("password"));
    passwordInput.clear();
    passwordInput.sendKeys(password);
    driver.findElement(By.cssSelector(".el-button")).click();
    // Check result
    if (success) {
      WebElement contentToCheck =
          new WebDriverWait(driver, Duration.ofSeconds(10).toSeconds())
              .until(driver -> driver.findElement(By.cssSelector(".no-redirect")));
      assertEquals("项目列表", contentToCheck.getText());
    } else {
      WebElement contentToCheck =
          new WebDriverWait(driver, Duration.ofSeconds(10).toSeconds())
              .until(driver -> driver.findElement(By.cssSelector(".el-message__content")));
      assertEquals(errorMessage, contentToCheck.getText());
    }
  }
}
