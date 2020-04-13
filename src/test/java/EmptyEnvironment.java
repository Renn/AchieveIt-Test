import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class EmptyEnvironment {

  static final User TEST_USER =
      new User(
          "testUserId",
          "testUsername",
          "testPassword",
          "test_user@mail.com",
          "testUserPhone",
          0,
          0);
  static final Client TEST_CLIENT =
      new Client(
          "0101",
          "testContactName",
          "testCompany",
          "test_client@mail.com",
          "testClientPhone",
          "testAddress",
          0,
          false);
  static final Project TEST_PROJECT =
      new Project(
          "2020-0101-D-01",
          "test project 01",
          TEST_USER.getId(),
          TEST_USER.getId(),
          TEST_CLIENT.getId(),
          new Date(1577808000000L),
          new Date(1577808000000L),
          "MVC",
          "[\"C++\",\"Java\"]",
          "[{\"milestoneContent\":\"milestone 1\",\"milestoneDate\":1577808000000},{\"milestoneContent\":\"milestone 2\",\"milestoneDate\":1577808000000},{\"milestoneContent\":\"milestone 3\",\"milestoneDate\":1577808000000}]",
          0);
  static final String TEST_PROJECTID = "2019-0101-S-01";
  static final Project TEST_PROJECT_02 =
      new Project(
          TEST_PROJECTID,
          "test project 02",
          TEST_USER.getId(),
          TEST_USER.getId(),
          TEST_CLIENT.getId(),
          new Date(1577808000000L),
          new Date(1577808000000L),
          "MVC");
  static JavascriptExecutor js;
  static WebDriver driver;
  static Map<String, Object> vars;

  @BeforeAll
  static void beforeAll() {
    driver = Driver.getDriver();
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
  }
}
