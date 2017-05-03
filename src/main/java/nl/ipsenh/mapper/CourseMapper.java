package nl.ipsenh.mapper;

import nl.ipsenh.model.Course;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Jamie on 3-5-2017.
 */
public class CourseMapper implements ResultSetMapper<Course> {

    @Override
    public Course map(int i, ResultSet r, StatementContext statementContext) throws SQLException {
        return new Course(r.getString("code"), r.getString("description"),
                r.getDate("start_date"), r.getDate("end_date"));
    }
}
