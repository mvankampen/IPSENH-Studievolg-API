package nl.ipsenh.resource;

import nl.ipsenh.model.ABRequirement;
import nl.ipsenh.service.ABRequirementService;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author Lorenzo Jokhan
 * @version 1.0
 * @since 11/05/2017.
 */

@Path("ab-restrictions") @Produces(MediaType.APPLICATION_JSON) @Consumes(MediaType.APPLICATION_JSON)
public class ABRestrictionResource {
    private final ABRequirementService abRequirementService;

    public ABRestrictionResource(ABRequirementService abRequirementService) {
        this.abRequirementService = abRequirementService;
    }

    @POST public void insertABRequirement(ABRequirement requirement) {
        this.abRequirementService.createABRequirement(requirement);
    }


}
