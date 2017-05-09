package nl.ipsenh.mapper;

import nl.ipsenh.model.CourseRestriction;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Jamie on 3-5-2017.
 */
public class CourseRestrictionMapper implements ResultSetMapper<CourseRestriction> {

    @Override
    public CourseRestriction map(int i, ResultSet resultSet, StatementContext statementContext)
        throws SQLException {
        return new CourseRestriction(resultSet.getString("course"),
            resultSet.getString("restriction"));
    }
}
