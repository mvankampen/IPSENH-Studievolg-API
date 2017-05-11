package nl.ipsenh.service;

import nl.ipsenh.model.CourseOwner;
import nl.ipsenh.model.User;
import nl.ipsenh.persistence.CourseOwnerDAO;

import javax.ws.rs.ForbiddenException;
import java.util.Collection;

/**
 * @author Michael van Kampen
 * @version 1.0
 * @since 2017-05-08
 */
public class CourseOwnerService extends BaseService<User> {

    private CourseOwnerDAO courseOwnerDAO;
    private UserService userService;

    /**
     * Constructor
     *
     * @param courseOwnerDAO implementation of interface Database
     */
    public CourseOwnerService(CourseOwnerDAO courseOwnerDAO, UserService userService) {
        this.courseOwnerDAO = courseOwnerDAO;
        this.userService = userService;
    }

    /**
     * @param courseOwner {@link CourseOwner} object
     */
    public void insertCourseOwner(CourseOwner courseOwner) {
        User courseLeader =
            requireResult(this.userService.getUserByEmail(courseOwner.getCourseLeader()));
        if (!courseLeader.hasRole("moduleleider")) {
            throw new ForbiddenException("User is not a Moduleleider");
        }

        this.courseOwnerDAO.insertCourseOwner(courseOwner);

    }

    /**
     * @return {@link Collection} of {@link CourseOwner}
     */
    public Collection<CourseOwner> getAllCourseOwners() {
        return this.courseOwnerDAO.getAllCourseOwners();
    }

    public CourseOwner getCourseOwnerByEmail(String email) {
        return this.courseOwnerDAO.getCourseOwnerByEmail(email);
    }
}
