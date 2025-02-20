package test.com.explainjava.service;

import main.java.com.explainjava.domain.Supplier;
import main.java.com.explainjava.exceptions.IDNotUniqueException;
import main.java.com.explainjava.exceptions.ValidationException;
import main.java.com.explainjava.repository.SupplierRepository;
import main.java.com.explainjava.service.SupplierService;
import main.java.com.explainjava.validators.SupplierValidator;

public class SupplierServiceTest {
    private SupplierService supplierService;

    private void setUp() throws IDNotUniqueException, ValidationException {
	SupplierRepository supplierRepository = new SupplierRepository();
	SupplierValidator supplierValidator = new SupplierValidator();
	supplierService = new SupplierService(supplierRepository, supplierValidator);

    }

    public void shouldSaveSupplier_whenSavedMethodCalled() throws IDNotUniqueException, ValidationException {
	setUp();

	Supplier savedSupplier = supplierService.saveSupplier(1, "Lemonades", "contact@lemonades.com");

	assert savedSupplier != null;
	assert savedSupplier.getId() == 1;
	assert savedSupplier.getName().equals("Lemonades");
	assert supplierService.findById(1).getId() == 1;
    }

    public void shouldUpdateSupplier_whenUpdateMethodCalled() throws IDNotUniqueException, ValidationException {
	setUp();

	Supplier saveSupplier = supplierService.saveSupplier(1, "Lemonades", "contact@lemonades.com");
	Supplier updatedSupplier = supplierService.updateSupplier(1, "Burger", "contact@burgers.com");

	assert updatedSupplier != null;
	assert updatedSupplier.getId() == 1;
	assert updatedSupplier.getName().equals("Burger");
	assert updatedSupplier.getContactEmail().equals("contact@burgers.com");

    }

    public void shouldRemoveSupplier_whenRemoveMethodCalled() throws IDNotUniqueException, ValidationException {
	setUp();

	Supplier savedSupplier = supplierService.saveSupplier(1, "Lemonades", "contact@lemonades.com");
	supplierService.removeSupplier(1);

	Supplier deletedSupplier = supplierService.findById(1);
	assert deletedSupplier == null;
    }

    public void shouldFindSupplier_whenFindMethodCalled() throws IDNotUniqueException, ValidationException {
	setUp();

	supplierService.saveSupplier(1, "Lemonades", "contact@lemonades.com");
	supplierService.saveSupplier(2, "Water", "contact@water.com");

	Supplier firstSupplier = supplierService.findById(1);
	Supplier secondSupplier = supplierService.findById(2);

	assert firstSupplier.getId() == 1;
	assert secondSupplier.getId() == 2;
    }

    public void testAllService() throws IDNotUniqueException, ValidationException {
	shouldSaveSupplier_whenSavedMethodCalled();
	shouldUpdateSupplier_whenUpdateMethodCalled();
	shouldRemoveSupplier_whenRemoveMethodCalled();
	shouldFindSupplier_whenFindMethodCalled();
    }
}
