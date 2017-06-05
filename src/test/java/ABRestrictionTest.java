import io.dropwizard.testing.junit.ResourceTestRule;
import nl.ipsenh.model.ABRequirement;
import nl.ipsenh.persistence.ABRequirementDAO;
import nl.ipsenh.resource.ABRestrictionResource;
import nl.ipsenh.service.ABRequirementService;
import nl.ipsenh.service.CourseService;
import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;

/**
 * Created by Lorenzo Jokhan on 11/05/2017.
 */
public class ABRestrictionTest {

    private static final ABRequirementDAO dao = mock(ABRequirementDAO.class);
    private static final CourseService service = mock(CourseService.class);

    @ClassRule public static final ResourceTestRule resources = ResourceTestRule.builder()
        .addResource(new ABRestrictionResource(new ABRequirementService(dao, service))).build();

    private final ABRequirement abRequirement = new ABRequirement("IAD1", "IIAD");

    @Before public void setup() {

    }

    @Test public void insertABRestriction() {
        Response response =
            resources.target("/ab-restrictions").request().post(Entity.json(abRequirement));
        assertEquals(response.getStatus(), 204);
    }

    @After public void tearDown() {
        reset(dao);
        reset(service);
    }
}
