package nl.ipsenh.persistence;

import nl.ipsenh.mapper.RestrictionMapper;
import nl.ipsenh.model.Restriction;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.Collection;

/**
 * Created by Jamie on 3-5-2017.
 */
@RegisterMapper(RestrictionMapper.class)
public interface RestrictionDAO {

    @SqlQuery("SELECT * FROM course_restriction")
    Collection<Restriction> getAll();

    @SqlQuery("SELECT * FROM course_restriction WHERE course_code = :code")
    Collection<Restriction> getRestrictionByCode(@Bind("code") String code);
}
