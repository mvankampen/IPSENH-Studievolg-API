package nl.ipsenh.service;

import nl.ipsenh.model.Course;
import nl.ipsenh.model.Restriction;
import nl.ipsenh.persistence.RestrictionDAO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Jamie on 3-5-2017.
 */
public class RestrictionService extends BaseService<Restriction> {

    private final RestrictionDAO dao;

    public RestrictionService(RestrictionDAO dao) {
        this.dao = dao;
    }

    public Collection<Restriction> getAllRestrictions() {
        return dao.getAll();
    }

    public Collection<Restriction> getRestrictionByCode(String code) {
        return dao.getRestrictionByCode(code);
    }

    public Collection<Restriction> getRestrictionByCourse(Course course) {
        return dao.getRestrictionByCourse(course);
    }

    public void insertRestriction(Restriction restriction) {
        dao.insertRestriction(restriction);
    }

    public void updateRestriction(Restriction restriction, String courseCode) {
        dao.updateRestricion(restriction, courseCode);
    }
}
