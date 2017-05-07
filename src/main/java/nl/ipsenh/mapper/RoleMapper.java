package nl.ipsenh.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

/**
 * Created by Jamie on 25-4-2017.
 */
public class RoleMapper implements ResultSetMapper<String> {

  public String map(int index, ResultSet r, StatementContext ctx) throws SQLException {
    return r.getString("role_name");
  }
}
