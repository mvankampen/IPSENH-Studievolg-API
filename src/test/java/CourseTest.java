import io.dropwizard.testing.junit.ResourceTestRule;
import nl.ipsenh.model.Course;
import nl.ipsenh.persistence.CourseDAO;
import nl.ipsenh.resource.CourseResource;
import nl.ipsenh.service.CourseService;
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

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Created by Jamie on 9-5-2017.
 */
public class CourseTest {

    private static final CourseDAO dao = mock(CourseDAO.class);

    @ClassRule public static final ResourceTestRule resources =
        ResourceTestRule.builder().addResource(new CourseResource(new CourseService(dao))).build();

    private final Course expectedCourse =
        new Course("IIAD", "Inleiding Algoritmen en Datastructuren", null, null);

    private final ArrayList<Course> expectedCourses = new ArrayList<>();

    @Before public void setup() {
        for (int i = 0; i <= 10; i++) {
            expectedCourses.add(expectedCourse);
        }
        when(dao.getCourseByCode("IIAD")).thenReturn(expectedCourse);
        when(dao.getAll()).thenReturn(expectedCourses);
    }

    @Test public void getAllCourses() {
        Collection<Course> testCourses =
            resources.target("/courses").request().get(new GenericType<List<Course>>() {
            });
        assertEquals(expectedCourses.size(), testCourses.size());
        verify(dao).getAll();
    }

    @Test public void getCourseByCode() {
        Course testCourse = resources.target("/courses/IIAD").request().get(Course.class);
        assertEquals(expectedCourse.getCode(), testCourse.getCode());
        verify(dao).getCourseByCode("IIAD");
    }

    @Test public void insertCourse() {
        Response response =
            resources.target("/courses").request().post(Entity.json(expectedCourse));
        assertEquals(response.getStatus(), 204);
    }

    @Test public void updateCourse() {
        Response response =
            resources.target("/courses/IIAD").request().put(Entity.json(expectedCourse));
        assertEquals(response.getStatus(), 204);
    }

    @After public void tearDown() {
        reset(dao);
    }
}
