package main.java.com.explainjava.repository;

import java.util.HashMap;
import java.util.Map;

import main.java.com.explainjava.domain.Entity;
import main.java.com.explainjava.exceptions.IDNotUniqueException;

public class GenericRepository<T extends Entity> implements RepositoryInterface<T> {
    private Map<Integer, T> entities;

    public GenericRepository() {
	entities = new HashMap<>();
    }

    @Override
    public T save(T entity) throws IDNotUniqueException {
	if (entities.containsKey(entity.getId())) {
	    throw new IDNotUniqueException("The id is not unique");
	}
	entities.put(entity.getId(), entity);
	return entity;
    }

    public T update(T entity) {
	if (entities.containsKey(entity.getId())) {
	    entities.put(entity.getId(), entity);
	}
	return entity;
    }

    public void delete(int productId) {
	entities.remove(productId);
    }

    public Iterable<T> findAll() {
	return entities.values();
    }

    public T findById(int productId) {
	return entities.get(productId);
    }
}
