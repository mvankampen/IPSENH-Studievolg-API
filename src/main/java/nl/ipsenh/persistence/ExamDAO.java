package nl.ipsenh.persistence;

import nl.ipsenh.mapper.ExamMapper;
import nl.ipsenh.model.Exam;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.Collection;

/**
 * Created by Jamie on 23-5-2017.
 */
@RegisterMapper(ExamMapper.class)
public interface ExamDAO {

    @SqlQuery("SELECT * FROM Exam")
    Collection<Exam> getAll();

    @SqlQuery("SELECT * FROM Exam WHERE course_code = :course_code")
    Collection<Exam> getExamsByCourse(@Bind("course_code") String courseCode);

    @SqlUpdate("INSERT INTO Exam (exam_name, exam_weight, course_code) VALUES (:name, :weight, :courseCode)")
    void insertExam(@BindBean Exam exam);

    @SqlUpdate("UPDATE Exam SET exam_name = :name, exam_weight = :weight, course_code = :courseCode WHERE exam_name = :exam_name")
    void updateExam(@BindBean Exam exam, @Bind("exam_name") String examName);


}
