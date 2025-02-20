package test.com.explainjava.service;

import main.java.com.explainjava.domain.Supplier;
import main.java.com.explainjava.repository.SupplierRepository;
import main.java.com.explainjava.service.SupplierService;

public class SupplierServiceTest {
    private SupplierService supplierService;

    private void setUp() {
	SupplierRepository supplierRepository = new SupplierRepository();
	supplierService = new SupplierService(supplierRepository);
    }

    public void shouldSaveSupplier_whenSavedMethodCalled() {
	setUp();

	Supplier savedSupplier = supplierService.saveSupplier(1, "Lemonades", "contact@lemonades.com");

	assert savedSupplier != null;
	assert savedSupplier.getId() == 1;
	assert savedSupplier.getName().equals("Lemonades");
	assert supplierService.findById(1).getId() == 1;
    }

    public void shouldUpdateSupplier_whenUpdateMethodCalled() {
	setUp();

	Supplier saveSupplier = supplierService.saveSupplier(1, "Lemonades", "contact@lemonades.com");
	Supplier updatedSupplier = supplierService.updateSupplier(1, "Burger", "contact@burgers.com");

	assert updatedSupplier != null;
	assert updatedSupplier.getId() == 1;
	assert updatedSupplier.getName().equals("Burger");
	assert updatedSupplier.getContactEmail().equals("contact@burgers.com");

    }

    public void shouldRemoveSupplier_whenRemoveMethodCalled() {
	setUp();

	Supplier savedSupplier = supplierService.saveSupplier(1, "Lemonades", "contact@lemonades.com");
	supplierService.removeSupplier(1);

	Supplier deletedSupplier = supplierService.findById(1);
	assert deletedSupplier == null;
    }

    public void shouldFindSupplier_whenFindMethodCalled() {
	setUp();

	supplierService.saveSupplier(1, "Lemonades", "contact@lemonades.com");
	supplierService.saveSupplier(2, "Water", "contact@water.com");

	Supplier firstSupplier = supplierService.findById(1);
	Supplier secondSupplier = supplierService.findById(2);

	assert firstSupplier.getId() == 1;
	assert secondSupplier.getId() == 2;
    }

    public void testAllService() {
	shouldSaveSupplier_whenSavedMethodCalled();
	shouldUpdateSupplier_whenUpdateMethodCalled();
	shouldRemoveSupplier_whenRemoveMethodCalled();
	shouldFindSupplier_whenFindMethodCalled();
    }
}
