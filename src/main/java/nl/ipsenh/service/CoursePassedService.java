package nl.ipsenh.service;

import nl.ipsenh.model.CoursePassed;
import nl.ipsenh.model.User;
import nl.ipsenh.persistence.CoursePassedDAO;

/**
 * @author Lorenzo Jokhan, Michael van Kampen
 * @version 1.0
 * @since 2017-05-05
 */
public class CoursePassedService {

  private CoursePassedDAO coursePassedDAO;

  /**
   * @param coursePassedDAO implementation of interface Database
   */
  public CoursePassedService(CoursePassedDAO coursePassedDAO) {
    this.coursePassedDAO = coursePassedDAO;
  }

  /**
   * @param user {@link User} current User object
   * @return {@link CoursePassed} object
   */
  public CoursePassed getPassedCourse(String requiredCourse, User user) {
    return this.coursePassedDAO.getPassedCourse(requiredCourse, user);
  }
}
