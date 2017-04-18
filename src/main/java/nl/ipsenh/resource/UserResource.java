package nl.ipsenh.resource;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.annotation.JsonView;
import nl.ipsenh.View;
import nl.ipsenh.model.User;
import nl.ipsenh.service.UserService;

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
    @Timed
    public Collection<User> getAllUsers() {
        return service.getAllUsers();
    }

    @GET
    @JsonView(View.Public.class)
    @Path("/{id}")
    @Timed
    public User getUserById(@PathParam("id") long id) {
        return service.getUserById(id);
    }

    @POST
    @JsonView(View.Public.class)
    public void insertUser(User user) {
        System.out.println(user.getEmail());
        service.insertUser(user);
    }

    @PUT
    @JsonView(View.Public.class)
    @Path("/{id}")
    public void updateUser(@PathParam("id") long id, User user) {
        service.updateUser(id, user);
    }
}