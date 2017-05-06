package nl.ipsenh.mapper;

import nl.ipsenh.model.Restriction;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Jamie on 3-5-2017.
 */
public class RestrictionMapper implements ResultSetMapper<Restriction> {

    @Override
    public Restriction map(int i, ResultSet resultSet, StatementContext statementContext) throws SQLException {
        return new Restriction(resultSet.getString("course"), resultSet.getString("restriction"));
    }
}
