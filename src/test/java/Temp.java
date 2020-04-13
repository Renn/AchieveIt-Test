import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.jupiter.api.Test;

public class Temp {

  @Test
  void temp() throws Exception {
    Date date = new Date(1577808000000L);
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    System.out.print(format.format(date));

  }
}
