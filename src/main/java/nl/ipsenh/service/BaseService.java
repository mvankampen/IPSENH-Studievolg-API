package nl.ipsenh.service;

import javax.ws.rs.NotFoundException;

/**
 * Created by Jamie on 18-4-2017.
 */
public class BaseService<T> {

    public T requireResult(T model) {
        if (model == null) {
            throw new NotFoundException();
        }

        return model;
    }
}
