package nl.ipsenh.service;

import nl.ipsenh.model.ABRequirement;
import nl.ipsenh.model.Course;
import nl.ipsenh.persistence.ABRequirementDAO;

import javax.ws.rs.ForbiddenException;
import java.util.Collection;

/**
 * Created by Lorenzo Jokhan on 05/05/2017.
 */
public class ABRequirementService {

    private ABRequirementDAO abRequirementDAO;
    private CourseService courseService;

    /**
     * test
     * @param abRequirementDAO implementation of interface Database
     */
    public ABRequirementService(ABRequirementDAO abRequirementDAO, CourseService courseService) {
        this.abRequirementDAO = abRequirementDAO;
        this.courseService = courseService;
    }

    /**
     * @param course {@link Course} object
     * @return {@link Collection} of {@link ABRequirement}
     */
    public Collection<ABRequirement> getRequirements(Course course) {
        return this.abRequirementDAO.getABRestrictionsByCourse(course);
    }

    public void createABRequirement(ABRequirement requirement) {
        if (requirement.getCourse().equalsIgnoreCase(requirement.getRequiredCourse()))
            throw new ForbiddenException("You cannot asign a course to it`s self");
        this.courseService.getCourseByCode(requirement.getCourse());
        this.courseService.getCourseByCode(requirement.getRequiredCourse());
        this.abRequirementDAO.createABRequirement(requirement);
    }
}
