import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import org.junit.jupiter.api.Test;

public class Temp {

  @Test
  void temp() throws Exception {
    Connection connection = DatabaseService.getConnection();
    String sql = "UPDATE members SET memberrole=(?) WHERE projectid=(?) AND memberid=(?)";
    PreparedStatement preparedStatement = connection.prepareStatement(sql);
    for (int i = 0; i < 1000; i++) {
      preparedStatement.setString(1, "[]");
      preparedStatement.setString(2, "2032-0000-S-11");
      preparedStatement.setString(3, String.format("test_userid_%d", i));
      preparedStatement.execute();
    }
    DatabaseService.closeConnection();
  }

  @Test
  void temp2() {
    Timestamp timestamp = Timestamp.valueOf("2020-04-16 15:00:00");
    System.out.println(timestamp.getTime());
  }
}
