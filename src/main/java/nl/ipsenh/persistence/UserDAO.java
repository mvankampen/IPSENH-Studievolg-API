package nl.ipsenh.persistence;

import nl.ipsenh.mapper.UserMapper;
import nl.ipsenh.model.User;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.Collection;

/**
 * Created by Jamie on 13-4-2017.
 */
@RegisterMapper(UserMapper.class) public interface UserDAO {

    @SqlQuery("SELECT * FROM user_account") public Collection<User> getAll();

    @SqlQuery("SELECT * FROM user_account WHERE email = :email LIMIT 1")
    User getUserByEmail(@Bind("email") String email);

    @SqlUpdate("INSERT INTO user_account (first_name, insertion, last_name, email, date_of_birth, password, role) VALUES (:firstName, :insertion, :lastName, :email, :dateOfBirth, :password, :role)")
    void insertUser(@BindBean User user); //TODO: encrypt password SHA256 (db)

    @SqlUpdate("UPDATE user_account SET first_name = :firstName, insertion = :insertion, last_name = :lastName, email = :email, date_of_birth = :dateOfBirth, role = :role WHERE email = :userEmail")
    void updateUser(@BindBean User user,
        @Bind("userEmail") String userEmail); //TODO: encrypt password SHA256 (db))

    @SqlUpdate("UPDATE user_account SET password = :password WHERE email = :email")
    void updatePassword(@Bind("email") String email, @Bind("password") String password);
}
