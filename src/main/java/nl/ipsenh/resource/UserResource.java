package nl.ipsenh.resource;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.annotation.JsonView;
import io.dropwizard.auth.Auth;
import java.util.Collection;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import nl.ipsenh.View;
import nl.ipsenh.model.User;
import nl.ipsenh.service.UserService;

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
  @Path("/{email}")
  @Timed
  public User getUserById(@PathParam("email") String userEmail) {
    return service.getUserByEmail(userEmail);
  }

  @POST
  @JsonView(View.Public.class)
  @RolesAllowed("admin")
  public void insertUser(User user) {
    service.insertUser(user);
  }

  @PUT
  @JsonView(View.Protected.class)
  @Path("/{email}")
  @RolesAllowed("admin")
  public void updateUser(@PathParam("email") String userEmail, User user) {
    service.updateUser(userEmail, user);
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
  public User authenticate(@Auth User authenticator) {
    return authenticator;
  }
}
