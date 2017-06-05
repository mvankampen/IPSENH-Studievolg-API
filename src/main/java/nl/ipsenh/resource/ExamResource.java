package nl.ipsenh.resource;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.annotation.JsonView;
import nl.ipsenh.View;
import nl.ipsenh.model.Exam;
import nl.ipsenh.service.ExamService;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

/**
 * Created by Jamie on 23-5-2017.
 */
@Path("/exams")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ExamResource {

    private final ExamService service;

    public ExamResource(ExamService service) {
        this.service = service;
    }

    @GET
    @JsonView(View.Protected.class)
    @RolesAllowed("moduleleider,admin")
    @Timed
    public Collection<Exam> getAllExams() {
        return service.getAllExams();
    }

    @GET
    @Path("/{course}")
    @JsonView(View.Protected.class)
    @RolesAllowed("moduleleider,admin")
    @Timed
    public Collection<Exam> getExamsByCourse(@PathParam("course") String courseCode) {
        return service.getExamsByCourse(courseCode);
    }

    @POST
    @JsonView(View.Protected.class)
    @RolesAllowed("moduleleider,admin")
    public void insertExam(Exam exam) {
        service.insertExam(exam);
    }

    @PUT
    @Path("/{examName}")
    @JsonView(View.Protected.class)
    @RolesAllowed("moduleleider,admin")
    public void updateExam(Exam exam, @PathParam("examName") String examName) {
        System.out.println(examName.replace("%20", " "));
        service.updateExam(exam, examName.replace("%20", " "));
    }
}
