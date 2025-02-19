package main.java.com.explainjava.service;

import main.java.com.explainjava.domain.Supplier;
import main.java.com.explainjava.repository.SupplierRepository;

public class SupplierService {
    private SupplierRepository supplierRepository;

    public SupplierService(SupplierRepository supplierRepository) {
	this.supplierRepository = supplierRepository;
    }

    public Supplier saveSupplier(int id, String name, String contactEmail) {
	Supplier supplier = new Supplier(id, name, contactEmail);
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
    
    public Iterable<Supplier> findAll(){
	return this.supplierRepository.findAll();
    }
    
    public Supplier findById(int id) {
	return this.supplierRepository.findById(id);
    }
}
