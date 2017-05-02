package nl.ipsenh.mapper;

import nl.ipsenh.model.User;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Jamie on 13-4-2017.
 */
public class UserMapper implements ResultSetMapper<User> {

    public User map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        return new User(r.getString("email"), r.getString("password"),
                r.getString("first_name"), r.getString("insertion"), r.getString("last_name"),
                r.getDate("date_of_birth"), r.getString("role"));
    }
}
