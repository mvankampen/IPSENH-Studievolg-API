package nl.ipsenh.persistence;

import nl.ipsenh.mapper.CourseRestrictionMapper;
import nl.ipsenh.model.Course;
import nl.ipsenh.model.CourseRestriction;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.Collection;

/**
 * @author Jamie Kalloe
 * @version 1.0
 * @since 2017-05-03
 */
@RegisterMapper(CourseRestrictionMapper.class) public interface CourseRestrictionDAO {

    @SqlQuery("SELECT * FROM course_restriction") Collection<CourseRestriction> getAllRestriction();

    @SqlQuery("SELECT * FROM course_restriction WHERE course = :courseCode")
    Collection<CourseRestriction> getRestrictionByCourseCode(@Bind("courseCode") String courseCode);

    @SqlQuery("SELECT * FROM course_restriction WHERE course = :code")
    Collection<CourseRestriction> getRestrictionByCourse(@BindBean Course course);

    @SqlUpdate("INSERT INTO course_restriction (course_code, requirement) VALUES (:courseCode, :requirement)")
    void insertRestriction(@BindBean CourseRestriction restriction);

    @SqlUpdate("UPDATE course_restriction SET course_code = :courseCode, requirement = :requirement WHERE course_code = :courseCode")
    void updateRestricion(@BindBean CourseRestriction restriction, @Bind("courseCode") String code);
}
