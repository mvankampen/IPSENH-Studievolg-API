package nl.ipsenh.persistence;

import nl.ipsenh.mapper.EnrolledCourseMapper;
import nl.ipsenh.model.EnrolledCourse;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

/**
 * Created by Lorenzo Jokhan on 05/05/2017.
 */

@RegisterMapper(EnrolledCourseMapper.class) public interface EnrollmentDAO {

    @SqlQuery("SELECT * FROM course_enrollment WHERE user_email = :email AND course_code = :course_code LIMIT 1")
    EnrolledCourse get(@Bind("email") String email, @Bind("course_code") String courseCode);

    @SqlUpdate("INSERT INTO course_enrollment (user_email, course_code) VALUES (:user_email, :course_code)")
    void enroll(@Bind("user_email") String email, @Bind("course_code") String courseCode);
}
