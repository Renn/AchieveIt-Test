import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import org.junit.jupiter.api.Test;

public class Temp {

  @Test
  void temp() {
    GregorianCalendar gc = new GregorianCalendar(2020, 0, 1);
    SimpleDateFormat formatter = new SimpleDateFormat();
    System.out.print(gc.getTimeInMillis());
  }
}
