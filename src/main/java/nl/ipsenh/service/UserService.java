package nl.ipsenh.service;

import java.util.Collection;
import nl.ipsenh.model.User;
import nl.ipsenh.persistence.UserDAO;

/**
 * Created by Jamie on 13-4-2017.
 */
public class UserService extends BaseService<User> {

  private final UserDAO userDAO;

  /**
   * @param userDAO implementation of interface Database
   */
  public UserService(UserDAO userDAO) {
    this.userDAO = userDAO;
  }

  /**
   * @return {@link Collection} of {@link User}
   */
  public Collection<User> getAllUsers() {
    return this.userDAO.getAll();
  }

  /**
   * @param user insert {@link User} object.
   */
  public void insertUser(User user) {
    this.userDAO.insertUser(user);
  }

  /**
   * @param userEmail path param userEmail to update current user
   * @param user {@link User} object
   */
  public void updateUser(String userEmail, User user) {
    this.userDAO.updateUser(user, userEmail);
  }

  /**
   * @param email path param email to update current user password
   * @param user {@link User} object
   */
  public void updatePassword(String email, User user) {
    this.userDAO.updatePassword(email, user.getPassword());
  }

  /**
   * @param userEmail path param userEmail
   * @return {@link User} object
   */
  public User getUserByEmail(String userEmail) {
    User user = this.userDAO.getUserByEmail(userEmail);
    return requireResult(user);
  }
}
