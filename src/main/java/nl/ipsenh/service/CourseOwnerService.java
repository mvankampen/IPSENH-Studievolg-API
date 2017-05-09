package nl.ipsenh.service;

import com.oracle.javafx.jmx.json.JSONException;
import java.util.Collection;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;
import nl.ipsenh.model.CourseOwner;
import nl.ipsenh.model.User;
import nl.ipsenh.persistence.CourseOwnerDAO;

/**
 * @author Michael van Kampen
 * @version 1.0
 * @since 2017-05-08
 */
public class CourseOwnerService {

  private CourseOwnerDAO courseOwnerDAO;
  private UserService userService;

  /**
   * Constructor
   *
   * @param courseOwnerDAO implementation of interface Database
   */
  public CourseOwnerService(CourseOwnerDAO courseOwnerDAO, UserService userService) {
    this.courseOwnerDAO = courseOwnerDAO;
    this.userService = userService;
  }

  /**
   * @param courseOwner {@link CourseOwner} object
   */
  public void insertCourseOwner(CourseOwner courseOwner) throws JSONException {
    User courseLeader = this.userService.getUserByEmail(courseOwner.getCourseLeader());
    if (!courseLeader.hasRole("moduleleider")) {
      throw new WebApplicationException("User is not a Moduleleider", Status.FORBIDDEN);
    }

    this.courseOwnerDAO.insertCourseOwner(courseOwner);

  }

  /**
   *
   * @return {@link Collection} of {@link CourseOwner}
   */
  public Collection<CourseOwner> getAllCourseOwners() {
    return this.courseOwnerDAO.getAllCourseOwners();
  }

}
