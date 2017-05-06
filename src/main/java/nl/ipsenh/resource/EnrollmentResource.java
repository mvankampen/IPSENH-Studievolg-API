package nl.ipsenh.resource;

import com.fasterxml.jackson.annotation.JsonView;
import io.dropwizard.auth.Auth;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import nl.ipsenh.View;
import nl.ipsenh.model.User;
import nl.ipsenh.service.EnrollmentService;

/**
 * Created by Lorenzo Jokhan on 05/05/2017.
 */
@Path("/enrollment")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EnrollmentResource {
  private final EnrollmentService service;

  public EnrollmentResource(EnrollmentService service) {
    this.service = service;
  }

  /* Changed initial implementation from Course object to courseCode String
   * Checks must only be done on objects retrieved from the database.*/
  @POST
  @JsonView(View.Protected.class)
  @RolesAllowed("admin, moduleleider, cursist")
  @Path("/{courseCode}")
  public void enroll(@Auth User user, @PathParam("courseCode") String courseCode) {
    service.enrollToCourse(user, courseCode);
  }
}