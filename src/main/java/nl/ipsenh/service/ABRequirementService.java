package nl.ipsenh.service;

import nl.ipsenh.model.ABRequirement;
import nl.ipsenh.model.Course;
import nl.ipsenh.persistence.ABRequirementDAO;

import java.util.Collection;

/**
 * Created by Lorenzo Jokhan on 05/05/2017.
 */
public class ABRequirementService {

    private ABRequirementDAO abRequirementDAO;

    /**
     * @param abRequirementDAO implementation of interface Database
     */
    public ABRequirementService(ABRequirementDAO abRequirementDAO) {
        this.abRequirementDAO = abRequirementDAO;
    }

    /**
     * @param course {@link Course} object
     * @return {@link Collection} of {@link ABRequirement}
     */
    public Collection<ABRequirement> getRequirements(Course course) {
        return this.abRequirementDAO.getABRestrictionsByCourse(course);
    }
}
