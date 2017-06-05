package nl.ipsenh.service;

import nl.ipsenh.model.Exam;
import nl.ipsenh.persistence.ExamDAO;

import java.util.Collection;

/**
 * Created by Jamie on 23-5-2017.
 */
public class ExamService extends BaseService<Exam> {

    private final ExamDAO dao;

    public ExamService(ExamDAO dao) {
        this.dao = dao;
    }

    public Collection<Exam> getAllExams() {
        return dao.getAll();
    }

    public Collection<Exam> getExamsByCourse(String courseCode) {
        return dao.getExamsByCourse(courseCode);
    }

    public void insertExam(Exam exam) {
        dao.insertExam(exam);
    }

    public void updateExam(Exam exam, String examName) {
        dao.updateExam(exam, examName);
    }
}
