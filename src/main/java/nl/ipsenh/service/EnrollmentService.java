package nl.ipsenh.service;

import java.util.Collection;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import nl.ipsenh.model.Course;
import nl.ipsenh.model.EnrolledCourse;
import nl.ipsenh.model.Restriction;
import nl.ipsenh.model.User;
import nl.ipsenh.persistence.EnrollmentDAO;
import nl.ipsenh.restrictions.ABRestriction;
import nl.ipsenh.restrictions.CourseRestriction;
import nl.ipsenh.restrictions.DateRestriction;

/**
 * Created by Lorenzo Jokhan on 05/05/2017.
 */

/*
* This service is responsible for the delegating the enrollment process
* towards different implementations depending on the type of restriction.
* It uses different services to determine wether someone should be able to join a course.
*
* 1 - Get all types of restrictions that are placed on the course
* 2 - Foreach restriction validate if it has been met
* 3 -
*
*
* */
public class EnrollmentService {
  private EnrollmentDAO enrollmentDAO;
  private RestrictionService restrictionService;
  private ABRequirementService abRequirementService;
  private CoursePassedService coursePassedService;
  private CourseService courseService;


  public EnrollmentService(RestrictionService restrictionService,
      ABRequirementService abRequirementService,
      CoursePassedService coursePassedService,
      CourseService courseService,
      EnrollmentDAO dao) {
    this.restrictionService = restrictionService;
    this.abRequirementService = abRequirementService;
    this.coursePassedService = coursePassedService;
    this.courseService = courseService;
    this.enrollmentDAO = dao;
  }

  /** Get all used restriction types of the course **/
  public void enrollToCourse(User user, String courseCode) {
    // courses need to be gotten to validate with a correct AND UNMODIFIED course object!
    Course course = courseService.getCourseByCode(courseCode);
    Collection<Restriction> restrictions = restrictionService.getRestrictionByCourse(course);
    verifyEnrollment(user, course);

    for (Restriction restriction: restrictions) {
      runValidation(restriction, course, user);
    }

    enrollmentDAO.enroll(user.getEmail(), course.getCode());
  }

  /** Validate the restrictions according to their own implementation **/
  private void runValidation(Restriction restriction, Course course, User user) {
    CourseRestriction courseRestriction = getRestriction(restriction, course, user);
    if (courseRestriction != null) {
      courseRestriction.validate();
    }
  }

  /** Verify if the user is already enrolled for the course **/
  private void verifyEnrollment(User user, Course course) {
    EnrolledCourse enrolledCourse = enrollmentDAO.get(user.getEmail(), course.getCode());
    if(enrolledCourse != null) {
      throw new WebApplicationException(Response.status(Status.FORBIDDEN)
          .entity("You are already enrolled for this course")
          .type(MediaType.TEXT_PLAIN).build());
    }
  }

  /** Decide which interface to delegate the enrollment request to **/
  private CourseRestriction getRestriction(Restriction restriction, Course course, User user) {
    switch (restriction.getRequirement()) {
      case "AB_RESTRICTION":
        return new ABRestriction(course, abRequirementService, coursePassedService, user);
      case "DATE_RESTRICTION":
        return new DateRestriction(course);
      default:
        throw new WebApplicationException(Response.status(Status.BAD_REQUEST)
          .entity("Unknown restriction")
          .type(MediaType.TEXT_PLAIN).build());
    }
  }
}
