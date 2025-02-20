package main.java.com.explainjava.service;

import main.java.com.explainjava.domain.Supplier;
import main.java.com.explainjava.exceptions.IDNotUniqueException;
import main.java.com.explainjava.exceptions.ValidationException;
import main.java.com.explainjava.repository.SupplierRepository;
import main.java.com.explainjava.validators.SupplierValidator;

public class SupplierService {
    private SupplierRepository supplierRepository;

    private SupplierValidator supplierValidator;

    public SupplierService(SupplierRepository supplierRepository, SupplierValidator supplierValidator) {
	this.supplierRepository = supplierRepository;
	this.supplierValidator = supplierValidator;
    }

    public Supplier saveSupplier(int id, String name, String contactEmail)
	    throws ValidationException, IDNotUniqueException {
	Supplier supplier = new Supplier(id, name, contactEmail);
	supplierValidator.validateSupplier(supplier);
	Supplier savedSupplier = this.supplierRepository.save(supplier);
	return savedSupplier;
    }

    public void removeSupplier(int supplierId) {
	this.supplierRepository.delete(supplierId);
    }

    public Supplier updateSupplier(int id, String newName, String newContactEmail) {
	Supplier supplierToUpdate = new Supplier(id, newName, newContactEmail);
	Supplier updateSupplier = this.supplierRepository.update(supplierToUpdate);
	return updateSupplier;
    }

    public Iterable<Supplier> findAll() {
	return this.supplierRepository.findAll();
    }

    public Supplier findById(int id) {
	return this.supplierRepository.findById(id);
    }
}
