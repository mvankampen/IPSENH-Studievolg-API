package nl.ipsenh.resource;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.annotation.JsonView;
import java.util.Collection;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import nl.ipsenh.View;
import nl.ipsenh.model.CourseOwner;
import nl.ipsenh.service.CourseOwnerService;

/**
 * Created by Michael on 08-05-17.
 */
@Path("/courseowners")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CourseOwnerResource {

  private final CourseOwnerService courseOwnerService;

  public CourseOwnerResource(CourseOwnerService courseOwnerService) {
    this.courseOwnerService = courseOwnerService;
  }

  @POST
  @JsonView(View.Protected.class)
  @RolesAllowed("admin")
  public void inserCourseOwner(CourseOwner courseOwner) {
    this.courseOwnerService.insertCourseOwner(courseOwner);
  }

  @GET
  @JsonView(View.Protected.class)
  @RolesAllowed("admin")
  @Timed
  public Collection<CourseOwner> getAllCourseOwners() {
    return this.courseOwnerService.getAllCourseOwners();
  }

//  @GET
//  @JsonView(View.Protected.class)
//  @RolesAllowed("moduleleider,cursist,admin")
//  @Timed
//  public Collection<Course> getAllCourses() {
//    return service.getAllCourses();
//  }
//
//  @GET
//  @Path("/{code}")
//  @JsonView(View.Protected.class)
//  @RolesAllowed("moduleleider,cursist,admin")
//  @Timed
//  public Course getCourseByCode(@PathParam("code") String courseCode) {
//    return service.getCourseByCode(courseCode);
//  }
//
//
//
//  @PUT
//  @Path("/{code}")
//  @JsonView(View.Protected.class)
//  @RolesAllowed("admin,moduleleider")
//  public void updateCourse(@PathParam("code") String courseCode, Course course) {
//    service.updateCourse(course, courseCode);
//  }

}
