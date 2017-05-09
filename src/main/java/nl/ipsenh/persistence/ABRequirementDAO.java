package nl.ipsenh.persistence;

import nl.ipsenh.mapper.ABRequirementMapper;
import nl.ipsenh.model.ABRequirement;
import nl.ipsenh.model.Course;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.Collection;

/**
 * Created by Lorenzo Jokhan on 05/05/2017.
 */
@RegisterMapper(ABRequirementMapper.class) public interface ABRequirementDAO {

    @SqlQuery("SELECT * FROM ab_restriction WHERE course = :code")
    Collection<ABRequirement> getABRestrictionsByCourse(@BindBean Course course);

}
