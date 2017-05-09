package nl.ipsenh.mapper;

import nl.ipsenh.model.CourseOwner;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Michael van Kampen
 * @version 1.0
 * @since 2017-05-08
 */
public class CourseOwnerMapper implements ResultSetMapper<CourseOwner> {

    @Override public CourseOwner map(int i, ResultSet resultSet, StatementContext statementContext)
        throws SQLException {
        return new CourseOwner(resultSet.getString("course_code"),
            resultSet.getString("user_email"));
    }
}
