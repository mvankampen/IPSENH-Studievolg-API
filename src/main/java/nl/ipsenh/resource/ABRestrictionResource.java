package nl.ipsenh.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import nl.ipsenh.model.ABRequirement;
import nl.ipsenh.restrictions.ABRestriction;
import nl.ipsenh.service.ABRequirementService;

/**
 * @author Lorenzo Jokhan
 * @since 11/05/2017.
 * @version 1.0
 */

@Path("ab-restrictions")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ABRestrictionResource {
  private final ABRequirementService abRequirementService;

   public ABRestrictionResource(ABRequirementService abRequirementService) {
     this.abRequirementService = abRequirementService;
   }

   @POST
   public void insertABRequirement(ABRequirement requirement) {
     this.abRequirementService.createABRequirement(requirement);
   }


}
