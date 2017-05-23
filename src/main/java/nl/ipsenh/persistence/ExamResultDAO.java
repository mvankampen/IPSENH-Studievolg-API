package nl.ipsenh.persistence;

import nl.ipsenh.mapper.ExamResultMapper;
import nl.ipsenh.model.ExamResult;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.Collection;

/**
 * Created by Jamie on 23-5-2017.
 */
@RegisterMapper(ExamResultMapper.class)
public interface ExamResultDAO {

    @SqlQuery("SELECT * FROM Exam_result")
    Collection<ExamResult> getAll();

    @SqlQuery("SELECT * FROM Exam_result WHERE user_email = :user")
    Collection<ExamResult> getAllResultsForUser(@Bind("user") String userEmail);

    @SqlQuery("SELECT * FROM Exam_result WHERE exam_course = :course")
    Collection<ExamResult> getAllResultsForCourse(@Bind("course") String courseCode);

    @SqlQuery("SELECT * FROM Exam_result WHERE exam_name = :exam")
    Collection<ExamResult> getAllResultsForExam(@Bind("exam") String examName);

    @SqlUpdate("INSERT INTO Exam_result (exam_name, exam_course, exam_result, user_email) VALUES (:name, :courseCode, :result, :userEmail)")
    void insertResult(@BindBean ExamResult result);
}
