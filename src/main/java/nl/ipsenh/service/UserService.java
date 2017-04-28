package nl.ipsenh.service;

import nl.ipsenh.model.User;
import nl.ipsenh.persistence.UserDAO;

import java.util.Collection;

/**
 * Created by Jamie on 13-4-2017.
 */
public class UserService extends BaseService<User> {

    private final UserDAO dao;

    public UserService(UserDAO userDAO) {
        this.dao = userDAO;
    }

    public Collection<User> getAllUsers() {
        return this.dao.getAll();
    }

    public User getUserById(long id) {
        return requireResult(this.dao.getUserById(id));
    }

    public void insertUser(User user) {
        dao.insertUser(user.getFirstName(), user.getInsertion(), user.getLastName(),
                user.getEmail(), user.getDateOfBirth(), user.getPassword(), user.getRole());
    }

    public void updateUser(long id, User user) {
        dao.updateUser(user.getFirstName(), user.getInsertion(), user.getLastName(),
                user.getEmail(), user.getDateOfBirth(), user.getPassword(), user.getRole(), id);
    }
}
