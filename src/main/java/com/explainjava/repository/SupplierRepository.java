package main.java.com.explainjava.repository;

import main.java.com.explainjava.domain.Supplier;
import main.java.com.explainjava.exceptions.IDNotUniqueException;

import java.util.*;

public class SupplierRepository {
    private Map<Integer, Supplier> suppliers;

    public SupplierRepository() {
	this.suppliers = new HashMap<>();
    }

    public Supplier save(Supplier supplier) throws IDNotUniqueException {
	if (suppliers.containsKey(supplier.getId())) {
	    throw new IDNotUniqueException("The id is not unique");
	}
	suppliers.put(supplier.getId(), supplier);
	return supplier;
    }

    public Supplier update(Supplier supplier) {
	if (this.suppliers.containsKey(supplier.getId())) {
	    this.suppliers.put(supplier.getId(), supplier);
	}
	return supplier;
    }

    public void delete(int supplierId) {
	this.suppliers.remove(supplierId);
    }

    public Iterable<Supplier> findAll() {
	return this.suppliers.values();
    }

    public Supplier findById(int entityId) {
	return this.suppliers.get(entityId);
    }

}
