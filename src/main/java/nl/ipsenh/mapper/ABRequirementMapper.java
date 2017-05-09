package nl.ipsenh.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import nl.ipsenh.model.ABRequirement;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

/**
 * Created by Lorenzo Jokhan on 05/05/2017.
 */
public class ABRequirementMapper implements ResultSetMapper<ABRequirement> {

  @Override
  public ABRequirement map(int i, ResultSet resultSet, StatementContext statementContext)
      throws SQLException {
    return new ABRequirement(resultSet.getString("course"), resultSet.getString("required_course"));
  }
}
