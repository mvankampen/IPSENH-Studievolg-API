package nl.ipsenh.mapper;

import nl.ipsenh.model.ExamResult;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Jamie on 23-5-2017.
 */
public class ExamResultMapper implements ResultSetMapper<ExamResult> {

    @Override public ExamResult map(int index, ResultSet r, StatementContext ctx)
        throws SQLException {
        return new ExamResult(r.getString("exam_name"), r.getString("exam_course"),
            r.getDate("exam_date"), r.getDate("exam_mutation_date"), r.getDouble("exam_result"),
            r.getString("user_email"));
    }
}
