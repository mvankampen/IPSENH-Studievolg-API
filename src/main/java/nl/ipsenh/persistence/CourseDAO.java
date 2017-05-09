package nl.ipsenh.persistence;

import nl.ipsenh.mapper.CourseMapper;
import nl.ipsenh.model.Course;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.Collection;

/**
 * Created by Jamie on 3-5-2017.
 */
@RegisterMapper(CourseMapper.class) public interface CourseDAO {

    @SqlQuery("SELECT * FROM course") Collection<Course> getAll();

    @SqlQuery("SELECT * FROM COURSE WHERE code = :code LIMIT 1") Course getCourseByCode(
        @Bind("code") String code);

    @SqlUpdate("INSERT INTO course (code, description, start_date, end_date) VALUES (:code, :description, :startDate, :endDate)")
    void insertCourse(@BindBean Course course);

    @SqlUpdate("UPDATE course SET code = :code, description = :description, start_date = :startDate, end_date = :endDate WHERE code = :courseCode")
    void updateCourse(@BindBean Course course, @Bind("courseCode") String code);
}
