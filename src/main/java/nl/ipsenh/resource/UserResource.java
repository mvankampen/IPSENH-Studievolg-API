package nl.ipsenh.resource;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.annotation.JsonView;
import io.dropwizard.auth.Auth;
import nl.ipsenh.View;
import nl.ipsenh.model.User;
import nl.ipsenh.service.UserService;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

/**
 * Created by Jamie on 13-4-2017.
 */
@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

    private final UserService service;

    public UserResource(UserService userService) {
        this.service = userService;
    }

    //TODO: change JsonView classes to correct classes after authentication implementation!

    @GET
    @JsonView(View.Public.class)
    @RolesAllowed("admin")
    @Timed
    public Collection<User> getAllUsers() {
        return service.getAllUsers();
    }

    @GET
    @JsonView(View.Public.class)
    @RolesAllowed("admin")
    @Path("/{id}")
    @Timed
    public User getUserById(@PathParam("id") long id) {
        return service.getUserById(id);
    }

    @POST
    @JsonView(View.Public.class)
    @RolesAllowed("admin")
    public void insertUser(User user) {
        service.insertUser(user);
    }

    @PUT
    @JsonView(View.Protected.class)
    @Path("/{id}")
    @RolesAllowed("admin")
    public void updateUser(@PathParam("id") long id, User user) {
        service.updateUser(id, user);
    }

    @PUT
    @JsonView(View.Protected.class)
    @Path("update/{email}")
    public void updatePassword(@PathParam("email") String email, User user) {
        service.updatePassword(email, user);
    }

    @GET
    @Path("/me")
    @JsonView(View.Private.class)
    public User authenticate(@Auth User authenticator)
    {
        return authenticator;
    }
}
