package nl.ipsenh.restrictions;

import nl.ipsenh.model.Course;

import javax.ws.rs.ForbiddenException;
import java.sql.Date;

/**
 * @author Michael van Kampen & Lorenzo Jokhan
 * @version 1.0
 * @since 2017-05-05
 */
public class DateRestriction implements Restriction {

    private Course course;

    public DateRestriction(Course course) {
        this.course = course;
    }

    @Override public void validate() {
        Date date = new Date(System.currentTimeMillis());
        int compareResult = date.compareTo(course.getStartDate());

        if (compareResult != -1) {
            throw new ForbiddenException(
                "The course: " + course.getCode() + " cannot be joined at the current time");
        }
    }
}
