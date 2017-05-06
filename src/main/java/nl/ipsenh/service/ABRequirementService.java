package nl.ipsenh.service;

import java.util.Collection;
import nl.ipsenh.model.ABRequirement;
import nl.ipsenh.model.Course;
import nl.ipsenh.persistence.ABRequirementDAO;

/**
 * Created by Lorenzo Jokhan on 05/05/2017.
 */
public class ABRequirementService {
  private ABRequirementDAO dao;

  public ABRequirementService(ABRequirementDAO abRequirementDAO) {
    this.dao = abRequirementDAO;
  }

  public Collection<ABRequirement> getRequirements(Course course) {
    return this.dao.getABRestrictionsByCourse(course);
  }
}
