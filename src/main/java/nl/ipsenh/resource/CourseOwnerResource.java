package nl.ipsenh.resource;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.annotation.JsonView;
import nl.ipsenh.View;
import nl.ipsenh.model.CourseOwner;
import nl.ipsenh.service.CourseOwnerService;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

/**
 * Created by Michael on 08-05-17.
 */
@Path("/courseowners") @Produces(MediaType.APPLICATION_JSON) @Consumes(MediaType.APPLICATION_JSON)
public class CourseOwnerResource {

    private final CourseOwnerService courseOwnerService;

    public CourseOwnerResource(CourseOwnerService courseOwnerService) {
        this.courseOwnerService = courseOwnerService;
    }

    @POST @JsonView(View.Protected.class) @RolesAllowed("admin")
    public void insertCourseOwner(CourseOwner courseOwner) {
        this.courseOwnerService.insertCourseOwner(courseOwner);
    }

    @GET @JsonView(View.Protected.class) @RolesAllowed("admin") @Timed
    public Collection<CourseOwner> getAllCourseOwners() {
        return this.courseOwnerService.getAllCourseOwners();
    }

    @GET @Path("/{email}") @JsonView(View.Protected.class) @RolesAllowed("admin") @Timed
    public CourseOwner getCourseOwnerByEmail(@PathParam("email") String email) {
        return this.courseOwnerService.getCourseOwnerByEmail(email);
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
