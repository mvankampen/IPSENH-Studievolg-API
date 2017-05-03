package nl.ipsenh.service;

import nl.ipsenh.model.Course;
import nl.ipsenh.persistence.CourseDAO;

import java.util.Collection;

/**
 * Created by Jamie on 3-5-2017.
 */
public class CourseService extends BaseService<Course> {

    private final CourseDAO dao;

    public CourseService(CourseDAO dao) {
        this.dao = dao;
    }

    public Collection<Course> getAllCourses() {
        return dao.getAll();
    }

    public Course getCourseByCode(String code) {
        return requireResult(dao.getCourseByCode(code));
    }

    public void insertCourse(Course course) {
        dao.insertCourse(course);
    }

    public void updateCourse(Course course, String courseCode) {
        dao.updateCourse(course, courseCode);
    }
}
