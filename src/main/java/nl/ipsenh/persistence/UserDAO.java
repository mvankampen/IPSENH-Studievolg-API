package nl.ipsenh.persistence;

import nl.ipsenh.mapper.UserMapper;
import nl.ipsenh.model.User;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.sql.Date;
import java.util.Collection;

/**
 * Created by Jamie on 13-4-2017.
 */
@RegisterMapper(UserMapper.class)
public interface UserDAO {

    @SqlQuery("SELECT * FROM user_account")
    public Collection<User> getAll();

    @SqlQuery("SELECT * FROM user_account WHERE id = :id LIMIT 1")
    public User getUserById(@Bind("id") long id);

    @SqlUpdate("INSERT INTO user_account (first_name, insertion, last_name, email, date_of_birth, password, role) VALUES (:first_name, :insertion, :last_name, :email, :date_of_birth, :password, :role)")
    public void insertUser(@Bind("first_name") String firstName, @Bind("insertion") String insertion,
                           @Bind("last_name") String last_name, @Bind("email") String email,
                           @Bind("date_of_birth") Date dateOfBirth, @Bind("password") String password,
                           @Bind("role") String role); //TODO: encrypt password SHA256 (db)
}
