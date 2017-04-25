package nl.ipsenh.service;

import nl.ipsenh.persistence.RoleDAO;

import java.util.Collection;

/**
 * Created by Jamie on 25-4-2017.
 */
public class RoleService extends BaseService<String> {

    private final RoleDAO dao;

    public RoleService(RoleDAO roleDAO) {
        this.dao = roleDAO;
    }

    public Collection<String> getAllRoles() {
        return dao.getAll();
    }
}
