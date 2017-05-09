package nl.ipsenh.persistence;

import nl.ipsenh.mapper.CoursePassedMapper;
import nl.ipsenh.model.CoursePassed;
import nl.ipsenh.model.User;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

/**
 * Created by Lorenzo Jokhan on 05/05/2017.
 */

@RegisterMapper(CoursePassedMapper.class) public interface CoursePassedDAO {

    @SqlQuery("SELECT * FROM course_passed WHERE course = :code AND user_email = :email LIMIT 1")
    CoursePassed getPassedCourse(@Bind("code") String requiredCourse, @BindBean User user);

}
