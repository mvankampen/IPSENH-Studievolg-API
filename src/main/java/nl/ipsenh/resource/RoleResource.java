package nl.ipsenh.resource;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.annotation.JsonView;
import nl.ipsenh.View;
import nl.ipsenh.service.RoleService;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

/**
 * Created by Jamie on 25-4-2017.
 */
@Path("/roles")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RoleResource {

    private final RoleService service;

    public RoleResource(RoleService roleService) {
        this.service = roleService;
    }

    @GET
    @JsonView(View.Public.class)
    @Timed
    public Collection<String> getAll() {
        return service.getAllRoles(); //TODO authenticate! view is not public, only certain roles may see this
    }
}
