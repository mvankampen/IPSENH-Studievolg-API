package nl.ipsenh.resource;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.annotation.JsonView;
import nl.ipsenh.View;
import nl.ipsenh.model.Course;
import nl.ipsenh.service.CourseService;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

/**
 * Created by Jamie on 3-5-2017.
 */
@Path("/courses") @Produces(MediaType.APPLICATION_JSON) @Consumes(MediaType.APPLICATION_JSON)
public class CourseResource {

    private final CourseService service;

    public CourseResource(CourseService service) {
        this.service = service;
    }

    @GET @JsonView(View.Protected.class) @RolesAllowed("moduleleider,cursist,admin") @Timed
    public Collection<Course> getAllCourses() {
        return service.getAllCourses();
    }

    @GET @Path("/{code}") @JsonView(View.Protected.class)
    @RolesAllowed("moduleleider,cursist,admin") @Timed
    public Course getCourseByCode(@PathParam("code") String courseCode) {
        return service.getCourseByCode(courseCode);
    }

    @POST @JsonView(View.Protected.class) @RolesAllowed("admin,moduleleider")
    public void insertCourse(Course course) {
        service.insertCourse(course);
    }

    @PUT @Path("/{code}") @JsonView(View.Protected.class) @RolesAllowed("admin,moduleleider")
    public void updateCourse(@PathParam("code") String courseCode, Course course) {
        service.updateCourse(course, courseCode);
    }
}
