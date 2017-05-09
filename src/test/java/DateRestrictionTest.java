import java.sql.Date;
import java.util.Calendar;
import javax.ws.rs.WebApplicationException;
import nl.ipsenh.model.Course;
import nl.ipsenh.restrictions.DateRestriction;
import nl.ipsenh.restrictions.Restriction;
import org.junit.Test;

/**
 * @author Lorenzo Jokhan
 * @since 09/05/2017
 * @version 1.0
 */
public class DateRestrictionTest {

  @Test(expected = WebApplicationException.class)
  public void rejectRestriction() {
    Date courseStartDate = new Date(System.currentTimeMillis());
    Date courseEndDate = Date.valueOf("2020-02-10");
    Course course = new Course("IIAD", "Algoritms and data structures", courseStartDate, courseEndDate);
    Restriction dateRestriction = new DateRestriction(course);
    dateRestriction.validate();
  }

  @Test
  public void resolveRestriction() {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(new Date(System.currentTimeMillis()));
    calendar.add(Calendar.DATE, 10);

    Date startDate = new Date(calendar.getTimeInMillis());
    Date courseEndDate = Date.valueOf("2020-02-10");
    Course course = new Course("IPSENH", "Final Ipsen project", startDate, courseEndDate);
    Restriction dateRestriction = new DateRestriction(course);
    dateRestriction.validate();
  }
}
