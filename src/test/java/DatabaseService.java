import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseService {

  private static Connection connection = null;

  private DatabaseService() {
  }

  public static Connection getConnection() throws Exception {
    if (connection == null) {
      Class.forName("org.postgresql.Driver");
      String dbUrl = "jdbc:postgresql://101.37.205.16:5432/achievelt";
      String dbUsername = "postgres";
      String dbPassword = "wh123456";
      connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
    }
    return connection;
  }

  public static void closeConnection() throws Exception {
    if (connection != null) {
      connection.close();
    }
  }
}
