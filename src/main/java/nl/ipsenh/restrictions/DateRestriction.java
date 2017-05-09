package nl.ipsenh.restrictions;

import java.sql.Date;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import nl.ipsenh.model.Course;

/**
 * Created by Lorenzo Jokhan on 05/05/2017.
 */
public class DateRestriction implements Restriction {

  private Course course;

  public DateRestriction(Course course) {
    this.course = course;
  }

  @Override
  public void validate() {
    Date date = new Date(System.currentTimeMillis());
    int compareResult = date.compareTo(course.getStartDate());

    if (compareResult != -1) {
      throw new ForbiddenException("The course: " + course.getCode() + " cannot be joined at the current time");
    }
  }
}
