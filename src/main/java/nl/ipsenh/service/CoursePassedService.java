package nl.ipsenh.service;

import java.util.Collection;
import nl.ipsenh.model.CoursePassed;
import nl.ipsenh.model.User;
import nl.ipsenh.persistence.CoursePassedDAO;

/**
 * Created by Lorenzo Jokhan on 05/05/2017.
 */
public class CoursePassedService {
  private CoursePassedDAO dao;

  public CoursePassedService(CoursePassedDAO coursePassedDAO) {
    this.dao = coursePassedDAO;
  }

  public CoursePassed getPassedCourse(String requiredCourse, User user) {
    return this.dao.getPassedCourse(requiredCourse, user);
  }
}
