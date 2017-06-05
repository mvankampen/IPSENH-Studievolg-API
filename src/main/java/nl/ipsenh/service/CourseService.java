package nl.ipsenh.service;

import nl.ipsenh.model.Course;
import nl.ipsenh.model.User;
import nl.ipsenh.persistence.CourseDAO;

import java.util.Collection;

/**
 * @author Jamie Kalloe
 * @version 1.0
 * @since 2017-05-03
 */
public class CourseService extends BaseService<Course> {


    private final CourseDAO courseDAO;

    /**
     * Constructor
     *
     * @param courseDAO implementation of interface Database
     */
    public CourseService(CourseDAO courseDAO) {
        this.courseDAO = courseDAO;
    }

    /**
     * @return {@link Collection} of {@link Course}
     */
    public Collection<Course> getAllCourses() {
        return courseDAO.getAll();
    }

    public Collection<Course> getMyCourses(User user) {
        return courseDAO.getMyCourses(user);
    }

    /**
     * @param courseCode path param of courseCode
     * @return {@link Course} object by giving courseCode
     */
    public Course getCourseByCode(String courseCode) {
        return requireResult(courseDAO.getCourseByCode(courseCode));
    }

    /**
     * @param course {@link Course} object to insert into Database
     */
    public void insertCourse(Course course) {
        courseDAO.insertCourse(course);
    }

    /**
     * @param course     {@link Course} object
     * @param courseCode path param courseCode to update current Course object.
     */
    public void updateCourse(Course course, String courseCode) {
        courseDAO.updateCourse(course, courseCode);
    }
}
