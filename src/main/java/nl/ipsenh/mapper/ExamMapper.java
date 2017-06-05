package nl.ipsenh.mapper;

import nl.ipsenh.model.Exam;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Jamie on 23-5-2017.
 */
public class ExamMapper implements ResultSetMapper<Exam> {

    @Override public Exam map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        return new Exam(r.getString("exam_name"), r.getInt("exam_weight"),
            r.getString("course_code"));
    }
}
