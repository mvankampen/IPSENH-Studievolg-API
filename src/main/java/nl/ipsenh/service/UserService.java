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

    public void insertUser(User user) {
        dao.insertUser(user);
    }

    public void updateUser(String userEmail, User user) {
        dao.updateUser(user, userEmail);
    }

    public void updatePassword(String email, User user) {
        dao.updatePassword(email, user.getPassword());
    }

    public User getUserByEmail(String userEmail) {
        return dao.getUserByEmail(userEmail);
    }
}
