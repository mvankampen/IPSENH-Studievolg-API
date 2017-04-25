package nl.ipsenh.persistence;

import nl.ipsenh.mapper.RoleMapper;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.Collection;

/**
 * Created by Jamie on 25-4-2017.
 */
@RegisterMapper(RoleMapper.class)
public interface RoleDAO {

    @SqlQuery("SELECT * FROM User_role")
    public Collection<String> getAll();
}
