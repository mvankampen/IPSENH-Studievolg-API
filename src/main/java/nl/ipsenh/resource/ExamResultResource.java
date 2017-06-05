package nl.ipsenh.resource;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.annotation.JsonView;
import io.dropwizard.auth.Auth;
import nl.ipsenh.View;
import nl.ipsenh.model.ExamResult;
import nl.ipsenh.model.User;
import nl.ipsenh.service.ExamResultService;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

/**
 * Created by Jamie on 23-5-2017.
 */
@Path("results") @Produces(MediaType.APPLICATION_JSON) @Consumes(MediaType.APPLICATION_JSON)
public class ExamResultResource {

    private final ExamResultService service;

    public ExamResultResource(ExamResultService service) {
        this.service = service;
    }

    @GET @JsonView(View.Protected.class) @RolesAllowed("moduleleider,admin") @Timed
    public Collection<ExamResult> getAll() {
        return service.getAll();
    }

    @GET @Path("/user/{user}") @JsonView(View.Protected.class) @RolesAllowed("moduleleider,admin")
    @Timed public Collection<ExamResult> getAllResultsForUser(@PathParam("user") String userEmail) {
        return service.getAllForUser(userEmail);
    }

    @GET @Path("/course/{course}") @JsonView(View.Protected.class)
    @RolesAllowed("moduleleider,admin") @Timed
    public Collection<ExamResult> getAllResultsForCourse(@PathParam("course") String course) {
        return service.getAllForCourse(course.replace("%20", " "));
    }

    @GET @Path("/exam/{exam}") @JsonView(View.Protected.class) @RolesAllowed("moduleleider,admin")
    @Timed public Collection<ExamResult> getAllResultsForExam(@PathParam("exam") String exam) {
        return service.getAllForExam(exam);
    }

    @POST @JsonView(View.Protected.class) @RolesAllowed("moduleleider,admin")
    public void insertExam(ExamResult result) {
        service.insertResult(result);
    }

    @GET @RolesAllowed("cursist") @Path("/me") @Timed
    public Collection<ExamResult> getResultsForMe(@Auth User cursist) {
        return getAllResultsForUser(cursist.getEmail());
    }
}
