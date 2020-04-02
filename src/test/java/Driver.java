import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Driver {

  private static WebDriver driver = null;
  private static String[] args = {
//      "headless",
//      "start-maximized",
      "window-size=1552,840"
  };

  private Driver() {

  }

  public static WebDriver getDriver() {
    if (driver == null) {
      ChromeOptions options = new ChromeOptions();
      options.addArguments(args);
      driver = new ChromeDriver(options);
      //driver.manage().window().setSize(new Dimension(1552, 840));
    }
    return driver;
  }

  public static void quit() {
    if (driver != null) {
      driver.quit();
    }
  }
}
