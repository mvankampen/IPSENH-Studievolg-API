package nl.ipsenh.service;

import nl.ipsenh.model.Course;
import nl.ipsenh.model.CourseRestriction;
import nl.ipsenh.model.EnrolledCourse;
import nl.ipsenh.model.User;
import nl.ipsenh.persistence.EnrollmentDAO;
import nl.ipsenh.restrictions.ABRestriction;
import nl.ipsenh.restrictions.DateRestriction;
import nl.ipsenh.restrictions.Restriction;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.ForbiddenException;
import java.util.Collection;

/**
 * This service is responsible for the delegating the enrollment process
 * towards different implementations depending on the type of restriction.
 * It uses different services to determine wether someone should be able to join a course.
 *
 * @author Lorenzo Jokhan, Michael van Kampen
 * @version 1.0
 * @since 2017-05-05
 */
public class EnrollmentService {

    private EnrollmentDAO enrollmentDAO;
    private RestrictionService restrictionService;
    private ABRequirementService abRequirementService;
    private CoursePassedService coursePassedService;
    private CourseService courseService;

    public EnrollmentService(RestrictionService restrictionService,
        ABRequirementService abRequirementService, CoursePassedService coursePassedService,
        CourseService courseService, EnrollmentDAO enrollmentDAO) {
        this.restrictionService = restrictionService;
        this.abRequirementService = abRequirementService;
        this.coursePassedService = coursePassedService;
        this.courseService = courseService;
        this.enrollmentDAO = enrollmentDAO;
    }

    /**
     * Get all used restriction Types of the course, courses need to be gotten to validate with a
     * correct and unmodified course object!
     *
     * @param user       {@link User} object current user
     * @param courseCode path param of courseCode
     */
    public void enrollToCourse(User user, String courseCode) {
        Course course = courseService.getCourseByCode(courseCode);
        verifyEnrollment(user, course);
        Collection<CourseRestriction> restrictions =
            restrictionService.getRestrictionByCourse(course);

        for (CourseRestriction restriction : restrictions) {
            runValidation(restriction, course, user);
        }

        enrollmentDAO.enroll(user.getEmail(), course.getCode());
    }

    /**
     * Validate the restrictions according to their own implementation
     *
     * @param restriction {@link CourseRestriction} object, contains Course object and
     * @param course      {@link Course} object
     * @param user        {@link User} object current user
     */
    private void runValidation(CourseRestriction restriction, Course course, User user) {
        Restriction courseRestriction = getRestriction(restriction, course, user);
        if (courseRestriction != null) {
            courseRestriction.validate();
        }
    }

    /**
     * Verify if the user is already enrolled for the course
     *
     * @param user {@link User} object current user
     */
    private void verifyEnrollment(User user, Course course) {
        EnrolledCourse enrolledCourse = enrollmentDAO.get(user.getEmail(), course.getCode());
        if (enrolledCourse != null) {
            throw new ForbiddenException("You are already enrolled for this course");
        }
    }

    /**
     * Decide which interface to delegate the enrollment request to
     *
     * @return {@link Restriction} interface implementation
     */
    private Restriction getRestriction(CourseRestriction restriction, Course course, User user) {
        switch (restriction.getRestrictionType()) {
            case "AB_RESTRICTION":
                return new ABRestriction(course, abRequirementService, coursePassedService, user);
            case "DATE_RESTRICTION":
                return new DateRestriction(course);
            default:
                throw new BadRequestException("Unknown restriction");
        }
    }
}
