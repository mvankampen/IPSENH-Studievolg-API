package nl.ipsenh.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import nl.ipsenh.model.EnrolledCourse;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

/**
 * Created by Lorenzo Jokhan on 06/05/2017.
 */
public class EnrolledCourseMapper implements ResultSetMapper<EnrolledCourse> {

  @Override
  public EnrolledCourse map(int i, ResultSet resultSet, StatementContext statementContext)
      throws SQLException {
    return new EnrolledCourse(resultSet.getString("course_code"),
        resultSet.getString("user_email"));
  }
}
