package nl.ipsenh.service;

import nl.ipsenh.model.Course;
import nl.ipsenh.model.ExamResult;
import nl.ipsenh.model.User;
import nl.ipsenh.persistence.ExamResultDAO;

import javax.ws.rs.ForbiddenException;
import java.util.Collection;

/**
 * Created by Jamie on 23-5-2017.
 */
public class ExamResultService extends BaseService<ExamResult> {

    private final ExamResultDAO dao;
    private final EnrollmentService enrollmentService;
    private final UserService userService;
    private final CourseService courseService;

    public ExamResultService(ExamResultDAO dao, EnrollmentService enrollmentService, UserService userService, CourseService courseService) {
        this.dao = dao;
        this.enrollmentService = enrollmentService;
        this.userService = userService;
        this.courseService = courseService;
    }

    public Collection<ExamResult> getAll() {
        return dao.getAll();
    }

    public Collection<ExamResult> getAllForUser(String userEmail) {
        return dao.getAllResultsForUser(userEmail);
    }

    public Collection<ExamResult> getAllForCourse(String courseCode) {
        return dao.getAllResultsForCourse(courseCode);
    }

    public Collection<ExamResult> getAllForExam(String exam) {
        return dao.getAllResultsForExam(exam);
    }

    public void insertResult(ExamResult result) {
        User cursist = userService.getUserByEmail(result.getUserEmail());
        Course takenCourse = courseService.getCourseByCode(result.getCourseCode());
        if(enrollmentService.verifyEnrollment(cursist, takenCourse)) {
            dao.insertResult(result);
        } else {
            throw new ForbiddenException("You are not enrolled in the " + result.getCourseCode() + " course!");
        }
    }
}
