package nl.ipsenh.persistence;

import java.util.Collection;
import nl.ipsenh.mapper.CourseOwnerMapper;
import nl.ipsenh.model.CourseOwner;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

/**
 * @author Michael van Kampen
 * @version 1.0
 * @since 2017-05-08
 */
@RegisterMapper(CourseOwnerMapper.class)
public interface CourseOwnerDAO {

  @SqlUpdate("INSERT INTO course_owner(user_email, course_code) VALUES (:courseLeader, :courseCode)")
  void insertCourseOwner(@BindBean CourseOwner courseOwner);

  @SqlQuery("SELECT * FROM course_owner")
  Collection<CourseOwner> getAllCourseOwners();

  @SqlQuery("SELECT * FROM course_owner WHERE user_email = :courseLeader")
  Collection<CourseOwner> getAllCoursesByOwner(@Bind("courseLeader") String courseLeader);

//  @SqlQuery("SELECT * FROM course")
//  Collection<Course> getAll();
//
//  @SqlQuery("SELECT * FROM COURSE WHERE code = :code LIMIT 1")
//  Course getCourseByCode(@Bind("code") String code);
//
//  @SqlUpdate("INSERT INTO course (code, description, start_date, end_date) VALUES (:code, :description, :startDate, :endDate)")
//  void insertCourse(@BindBean Course course);
//
//  @SqlUpdate("UPDATE course SET code = :code, description = :description, start_date = :startDate, end_date = :endDate WHERE code = :courseCode")
//  void updateCourse(@BindBean Course course, @Bind("courseCode") String code);

}
