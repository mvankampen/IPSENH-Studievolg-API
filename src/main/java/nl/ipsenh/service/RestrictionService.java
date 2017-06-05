package nl.ipsenh.service;

import nl.ipsenh.model.Course;
import nl.ipsenh.model.CourseRestriction;
import nl.ipsenh.persistence.CourseRestrictionDAO;

import java.util.Collection;

/**
 * @author Jamie Kalloe
 * @version 1.0
 * @since 2017-05-03
 */
public class RestrictionService extends BaseService<CourseRestriction> {

    private final CourseRestrictionDAO courseRestrictionDAO;

    /**
     * @param courseRestrictionDAO implementation of interface Database
     */
    public RestrictionService(CourseRestrictionDAO courseRestrictionDAO) {
        this.courseRestrictionDAO = courseRestrictionDAO;
    }

    /**
     * @return {@link Collection} of {@link CourseRestriction}
     */
    public Collection<CourseRestriction> getAllRestrictions() {
        return courseRestrictionDAO.getAllRestriction();
    }

    /**
     * @param code
     * @return
     */
    public Collection<CourseRestriction> getRestrictionByCourseCode(String code) {
        return courseRestrictionDAO.getRestrictionByCourseCode(code);
    }

    public Collection<CourseRestriction> getRestrictionByCourse(Course course) {
        return courseRestrictionDAO.getRestrictionByCourse(course);
    }

    public void insertRestriction(CourseRestriction restriction) {
        courseRestrictionDAO.insertRestriction(restriction);
    }

    public void updateRestriction(CourseRestriction restriction, String courseCode) {
        courseRestrictionDAO.updateRestricion(restriction, courseCode);
    }
}
