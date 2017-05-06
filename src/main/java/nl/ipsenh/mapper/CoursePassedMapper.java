package nl.ipsenh.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import nl.ipsenh.model.CoursePassed;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

/**
 * Created by Lorenzo Jokhan on 05/05/2017.
 */
public class CoursePassedMapper implements ResultSetMapper<CoursePassed> {

  @Override
  public CoursePassed map(int i, ResultSet resultSet, StatementContext statementContext) throws SQLException {
    return new CoursePassed(resultSet.getString("course"), resultSet.getString("user_email"));
  }
}

