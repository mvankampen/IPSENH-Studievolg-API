package nl.ipsenh.service;

import nl.ipsenh.model.Restriction;
import nl.ipsenh.persistence.RestrictionDAO;

import java.util.ArrayList;
import java.util.Collection;

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

    public Restriction getRestrictionByCode(String code) {
        Collection<Restriction> restrictions = dao.getRestrictionByCode(code);
        ArrayList<String> requirements = new ArrayList<>();
        String courseCode = "";
        for(Restriction restriction : restrictions) {
            courseCode = restriction.getCourseCode();
            for(String requirement : restriction.getRequirements()) {
                requirements.add(requirement);
            }
        }

        return new Restriction(courseCode, requirements.toArray(new String[0]));
    }
}
