package nl.ipsenh.service;

import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.Authorizer;
import io.dropwizard.auth.basic.BasicCredentials;
import nl.ipsenh.model.User;
import nl.ipsenh.persistence.UserDAO;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.Optional;


/**
 * Created by Jamie on 1-5-2017.
 */
public class AuthenticationService implements Authenticator<BasicCredentials, User>, Authorizer<User> {

    private final UserDAO userDAO;

    @Inject
    public AuthenticationService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public Optional<User> authenticate(BasicCredentials basicCredentials) throws AuthenticationException {
        User user = userDAO.getUserByEmail(basicCredentials.getUsername());

        if(user != null && user.getPassword().equals(basicCredentials.getPassword())) { //TODO: encrypt basic password!
            return Optional.of(user);
        }

        return Optional.empty();
    }

    public boolean authorize(User user, String roleName) {
        return Arrays.asList(roleName.split(",")).contains(user.getRole());
    }
}
