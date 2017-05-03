package nl.ipsenh.service;

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
//        Collection<Restriction> restrictions = dao.getRestrictionByCode(code);
//        ArrayList<String> requirements = new ArrayList<>();
//        String courseCode = "";
//        for(Restriction restriction : restrictions) {
//            courseCode = restriction.getCourseCode();
//            for(String requirement : restriction.getRequirements()) {
//                requirements.add(requirement);
//            }
//        }

        return dao.getRestrictionByCode(code);
    }

    public void insertRestriction(Restriction restriction) {
        dao.insertRestriction(restriction);
    }

    public void updateRestriction(Restriction restriction, String courseCode) {
        dao.updateRestricion(restriction, courseCode);
    }
}
