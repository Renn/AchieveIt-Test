import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class TestEnvironment {

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
          "testClientId",
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
  static JavascriptExecutor js;
  static WebDriver driver;
  static Map<String, Object> vars;

  @BeforeAll
  static void beforeAll() {
    driver = Driver.getDriver();
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
    final String insertUserSQL =
        "INSERT INTO users(userid,username,userpassword,usermail,userphone,userdepartment,userrole) VALUES(?,?,?,?,?,?,?)";
    final String insertProjectSQL =
        "INSERT INTO project(projectid,projectname,projectmanagerid,projectmonitorid,projectclientid,projectstartdate,projectenddate,projectframeworks,projectlanguages,projectmilestones,projectstatus) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
    final String insertClientSQL = "INSERT INTO client(clientid, clientcontactname, clientcompany, clientemail, clientphone, clientaddress, clientlevel, deleted) VALUES (?,?,?,?,?,?,?,?)";
    try {
      Connection connection = DatabaseService.getConnection();
      // Insert a valid user
      PreparedStatement preparedStatement = connection.prepareStatement(insertUserSQL);
      preparedStatement.setString(1, TEST_USER.getId());
      preparedStatement.setString(2, TEST_USER.getUsername());
      preparedStatement.setString(3, TEST_USER.getPassword());
      preparedStatement.setString(4, TEST_USER.getMail());
      preparedStatement.setString(5, TEST_USER.getPhone());
      preparedStatement.setInt(6, TEST_USER.getDepartment());
      preparedStatement.setInt(7, TEST_USER.getRole());
      preparedStatement.execute();
      //Insert a valid client
      preparedStatement = connection.prepareStatement(insertClientSQL);
      preparedStatement.setString(1, TEST_CLIENT.getId());
      preparedStatement.setString(2, TEST_CLIENT.getContactName());
      preparedStatement.setString(3, TEST_CLIENT.getCompany());
      preparedStatement.setString(4, TEST_CLIENT.getEmail());
      preparedStatement.setString(5, TEST_CLIENT.getPhone());
      preparedStatement.setString(6, TEST_CLIENT.getAddress());
      preparedStatement.setInt(7, TEST_CLIENT.getLevel());
      preparedStatement.setBoolean(8, TEST_CLIENT.isDeleted());
      preparedStatement.execute();
      // Insert a valid project
      preparedStatement = connection.prepareStatement(insertProjectSQL);
      preparedStatement.setString(1, TEST_PROJECT.getId());
      preparedStatement.setString(2, TEST_PROJECT.getName());
      preparedStatement.setString(3, TEST_PROJECT.getManagerId());
      preparedStatement.setString(4, TEST_PROJECT.getMonitorId());
      preparedStatement.setString(5, TEST_PROJECT.getClientId());
      preparedStatement.setDate(6, TEST_PROJECT.getStartDate());
      preparedStatement.setDate(7, TEST_PROJECT.getEndDate());
      preparedStatement.setString(8, TEST_PROJECT.getFrameworks());
      preparedStatement.setString(9, TEST_PROJECT.getLanguages());
      preparedStatement.setString(10, TEST_PROJECT.getMilestones());
      preparedStatement.setInt(11, TEST_PROJECT.getStatus());
      preparedStatement.execute();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @AfterAll
  static void afterAll() {
    Driver.quit();
    // Delete test data
    final String deleteUserSQL = "DELETE FROM users WHERE userid=(?)";
    final String deleteProjectSQL = "DELETE FROM project WHERE projectid=(?)";
    final String deleteClientSQL = "DELETE FROM client WHERE clientid=(?)";
    try {
      Connection connection = DatabaseService.getConnection();
      // Delete test users
      PreparedStatement preparedStatement = connection.prepareStatement(deleteUserSQL);
      preparedStatement.setString(1, TEST_USER.getId());
      preparedStatement.execute();
      // Delete test project
      preparedStatement = connection.prepareStatement(deleteProjectSQL);
      preparedStatement.setString(1, TEST_PROJECT.getId());
      preparedStatement.execute();
      // Delete test client
      preparedStatement = connection.prepareStatement(deleteClientSQL);
      preparedStatement.setString(1, TEST_CLIENT.getId());
      preparedStatement.execute();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        DatabaseService.closeConnection();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}
