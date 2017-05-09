package nl.ipsenh.resource;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.annotation.JsonView;
import java.util.Collection;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import nl.ipsenh.View;
import nl.ipsenh.model.CourseRestriction;
import nl.ipsenh.service.RestrictionService;

/**
 * @author Jamie Kalloe, Michael van Kampen and Lorenzo Jokhan
 * @since 2017-05-03
 * @version 1.0
 */
@Path("/restrictions")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestrictionResource {

  private final RestrictionService restrictionService;

  public RestrictionResource(RestrictionService restrictionService) {
    this.restrictionService = restrictionService;
  }

  @GET
  @Timed
  @JsonView(View.Protected.class)
  @RolesAllowed("admin,moduleleider,cursist")
  public Collection<CourseRestriction> getAllRestrictions() {
    return restrictionService.getAllRestrictions();
  }

  @GET
  @Timed
  @Path("/{courseCode}")
  @JsonView(View.Protected.class)
  @RolesAllowed("admin,moduleleider,cursist")
  public Collection<CourseRestriction> getRestrictionByCode(
      @PathParam("courseCode") String courseCode) {
    return restrictionService.getRestrictionByCourseCode(courseCode);
  }

  @POST
  @JsonView(View.Protected.class)
  @RolesAllowed("admin,moduleleider")
  public void insertRestriction(CourseRestriction restriction) {
    restrictionService.insertRestriction(restriction);
  }

  @PUT
  @Path("/{code}")
  @JsonView(View.Protected.class)
  @RolesAllowed("admin,moduleleider")
  public void updateRestriction(@PathParam("code") String code, CourseRestriction restriction) {
    restrictionService.updateRestriction(restriction, code);
  }
}
