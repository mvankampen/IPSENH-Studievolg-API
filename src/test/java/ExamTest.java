import io.dropwizard.testing.junit.ResourceTestRule;
import nl.ipsenh.model.Exam;
import nl.ipsenh.persistence.ExamDAO;
import nl.ipsenh.resource.ExamResource;
import nl.ipsenh.service.ExamService;
import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;

import static org.mockito.Mockito.*;

/**
 * Created by Jamie on 23-5-2017.
 */
public class ExamTest {

    private static final ExamDAO dao = mock(ExamDAO.class);

    @ClassRule
    public static final ResourceTestRule resources = ResourceTestRule.builder()
            .addResource(new ExamResource(new ExamService(dao)))
            .build();

    private final Exam expectedExam = new Exam("ToetsA", 50, "IPSENH");
    private final ArrayList<Exam> expectedExams = new ArrayList<>();

    @Before
    public void setup() {
        expectedExams.add(expectedExam);

        when(dao.getExamsByCourse("IPSENH")).thenReturn(expectedExams);
        when(dao.getAll()).thenReturn(expectedExams);
    }

    @Test
    public void getAllExams() {
        Collection<Exam> testExams = resources.target("/exams").request()
                .get(new GenericType<List<Exam>>(){});
        assertEquals(expectedExams.size(), testExams.size());
        verify(dao).getAll();
    }

    @Test
    public void getExamsByCourse() {
        Collection<Exam> testExams = resources.target("/exams/IPSENH").request()
                .get(new GenericType<List<Exam>>(){});
        assertEquals(expectedExams.size(), testExams.size());
        verify(dao).getExamsByCourse("IPSENH");
    }

    @Test
    public void insertCourse() {
        Response response = resources.target("/exams").request().post(Entity.json(expectedExam));
        assertEquals(response.getStatus(), 204);
    }

    @Test
    public void updateExam() {
        Response response = resources.target("/exams/ToetsA").request().put(Entity.json(expectedExam));
        assertEquals(response.getStatus(), 204);
    }

    @After
    public void tearDown() {
        reset(dao);
    }
}
