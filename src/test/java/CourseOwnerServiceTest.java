import io.dropwizard.testing.junit.ResourceTestRule;
import nl.ipsenh.model.CourseOwner;
import nl.ipsenh.model.User;
import nl.ipsenh.persistence.CourseOwnerDAO;
import nl.ipsenh.persistence.UserDAO;
import nl.ipsenh.resource.CourseOwnerResource;
import nl.ipsenh.service.CourseOwnerService;
import nl.ipsenh.service.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

/**
 * @author Michael van Kampen
 * @version 1.0
 * @since 2017-05-09
 */
public class CourseOwnerServiceTest {

    private static final CourseOwnerDAO COURSE_OWNER_DAO = mock(CourseOwnerDAO.class);
    private static final UserDAO USER_DAO = mock(UserDAO.class);
    private static final UserService USER_SERVICE = mock(UserService.class);

    @ClassRule public static final ResourceTestRule resources = ResourceTestRule.builder()
        .addResource(
            new CourseOwnerResource(new CourseOwnerService(COURSE_OWNER_DAO, USER_SERVICE)))
        .build();

    private final CourseOwner expectedCourseOwner =
        new CourseOwner("moduleleider@ipsenh.nl", "IAD1");
    private final User expectedUser =
        new User("moduleleider@ipsenh.nl", "", "", "", "", null, "moduleleider");

    @Before public void setup() {
        when(USER_SERVICE.getUserByEmail(eq("moduleleider@ipsenh.nl"))).thenReturn(expectedUser);
        when(COURSE_OWNER_DAO.getCourseOwnerByEmail(eq("moduleleider@ipsenh.nl")))
            .thenReturn(expectedCourseOwner);
    }

    @Test public void testGetCourseownerByEmail() {
        CourseOwner courseOwner = resources.target("/courseowners/moduleleider@ipsenh.nl").request()
            .get(CourseOwner.class);
        assertTrue(expectedCourseOwner.equals(courseOwner));
        verify(COURSE_OWNER_DAO).getCourseOwnerByEmail("moduleleider@ipsenh.nl");
    }

    @Test public void testInsertCourseOwner() {
        Response response =
            resources.target("/courseowners").request().post(Entity.json(expectedCourseOwner));
        assertEquals(response.getStatus(), 204);
    }

    @After public void tearDown() {
        // we have to reset the mock after each test because of the
        // @ClassRule, or use a @Rule as mentioned below.
        reset(COURSE_OWNER_DAO);
        reset(USER_DAO);
        reset(USER_SERVICE);
    }
}
