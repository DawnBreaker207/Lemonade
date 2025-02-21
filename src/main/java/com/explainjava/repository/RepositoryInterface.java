package main.java.com.explainjava.repository;

import main.java.com.explainjava.domain.Entity;
import main.java.com.explainjava.exceptions.IDNotUniqueException;
import main.java.com.explainjava.exceptions.ValidationException;

public interface RepositoryInterface<T extends Entity> {
    T save(T entity) throws IDNotUniqueException, ValidationException;

    T update(T entity);

    void delete(int entityId);

    Iterable<T> findAll();

    T findById(int entityId);

}
