package nl.ipsenh.restrictions;

import java.util.Collection;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import nl.ipsenh.model.ABRequirement;
import nl.ipsenh.model.Course;
import nl.ipsenh.model.CoursePassed;
import nl.ipsenh.model.User;
import nl.ipsenh.service.ABRequirementService;
import nl.ipsenh.service.CoursePassedService;

/**
 * Created by Lorenzo Jokhan on 05/05/2017.
 */
public class ABRestriction implements Restriction {

  private Course course;
  private ABRequirementService abRequirementService;
  private CoursePassedService coursePassedService;
  private User user;

  public ABRestriction(Course course, ABRequirementService service,
      CoursePassedService coursePassedService, User user) {
    this.course = course;
    this.abRequirementService = service;
    this.coursePassedService = coursePassedService;
    this.user = user;
  }

  // For each course in courses, find out if requirement has been met
  @Override
  public void validate() {
    Collection<ABRequirement> requirements = abRequirementService.getRequirements(course);

    for (ABRequirement requirement : requirements) {
      CoursePassed coursePassed = this.coursePassedService
          .getPassedCourse(requirement.getRequiredCourse(), user);

      if (coursePassed == null) {
        throw new WebApplicationException(Response.status(Status.FORBIDDEN)
            .entity("Restriction " + requirement.getRequiredCourse() + " not met of " + requirement
                .getCourse())
            .type(MediaType.TEXT_PLAIN).build());
      }
    }
  }
}
