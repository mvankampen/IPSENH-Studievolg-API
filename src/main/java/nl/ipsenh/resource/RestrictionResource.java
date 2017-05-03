package nl.ipsenh.resource;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.annotation.JsonView;
import nl.ipsenh.View;
import nl.ipsenh.model.Restriction;
import nl.ipsenh.service.RestrictionService;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

/**
 * Created by Jamie on 3-5-2017.
 */
@Path("/restrictions")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestrictionResource {

    private final RestrictionService service;

    public RestrictionResource(RestrictionService service) {
        this.service = service;
    }

    @GET
    @Timed
    @JsonView(View.Protected.class)
    @RolesAllowed("admin,moduleleider,cursist")
    public Collection<Restriction> getAllRestrictions() {
        return service.getAllRestrictions();
    }

    @GET
    @Timed
    @Path("/{code}")
    @JsonView(View.Protected.class)
    @RolesAllowed("admin,moduleleider,cursist")
    public Restriction getRestrictionByCode(@PathParam("code") String code) {
        return service.getRestrictionByCode(code);
    }
}
