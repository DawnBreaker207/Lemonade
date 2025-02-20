package test.com.explainjava.repository;

import main.java.com.explainjava.domain.Supplier;
import main.java.com.explainjava.repository.SupplierRepository;

public class SupplierRepositoryTest {
    public void shouldSaveOneElement_whenSaveIsCalled() {
//	Arrange
	SupplierRepository supplierRepository = new SupplierRepository();
	Supplier firstSupplierToSave = new Supplier(1, "Lemonades", "contact@lemonades.com");

//	Act
	Supplier firstSavedSupplier = supplierRepository.save(firstSupplierToSave);

//	Assert
	assert firstSavedSupplier != null;
	assert firstSavedSupplier.getId() == 1;
	assert firstSavedSupplier.getName().equals("Lemonades");
	assert supplierRepository.findById(2) == null;
    }

    public void shouldSaveTwoElements_whenSaveIsCalledTwice() {
	SupplierRepository supplierRepository = new SupplierRepository();

	Supplier firstSupplierToSave = new Supplier(1, "Lemonades", "contact@lemonades.com");
	Supplier firstSavedSupplier = supplierRepository.save(firstSupplierToSave);

	Supplier secondSupplierToSave = new Supplier(2, "Water", "contact@water.com");
	Supplier secondSavedSupplier = supplierRepository.save(secondSupplierToSave);

	assert firstSavedSupplier.getId() == 1;
	assert firstSavedSupplier.getName().equals("Lemonades");
	assert supplierRepository.findById(3) == null;

	assert secondSavedSupplier != null;
	assert secondSavedSupplier.getId() == 2;
	assert secondSavedSupplier.getName().equals("Water");

	assert supplierRepository.findById(1) != null;
	assert supplierRepository.findById(2) != null;

    }

    public void shouldUpdateSupplier_whenUpdateMethodCalled() {
	SupplierRepository supplierRepository = new SupplierRepository();

	Supplier supplierToUpdate = new Supplier(1, "Lemonades", "contact@lemonades.com");
	supplierRepository.save(supplierToUpdate);

	supplierToUpdate.setName("Burger");
	supplierToUpdate.setContactEmail("contact@burgers.com");

	Supplier updatedSupplier = supplierRepository.update(supplierToUpdate);
	assert updatedSupplier != null;
	assert updatedSupplier.getId() == 1;
	assert updatedSupplier.getName().equals("Burger");
	assert updatedSupplier.getContactEmail().equals("contact@burgers.com");
    }

    public void shouldDeleteSupplier_whenDeletedMethodIsCalled() {
	SupplierRepository supplierRepository = new SupplierRepository();

	Supplier supplierToDelete = new Supplier(1, "Lemonades", "contact@lemonades.com");
	supplierRepository.save(supplierToDelete);

	supplierRepository.delete(1);

	Supplier deletedSupplier = supplierRepository.findById(1);
	assert deletedSupplier == null;
    }

    public void shouldFindSupplier_whenFindMethodCalled() {
	SupplierRepository supplierRepository = new SupplierRepository();

	Supplier firstSupplierToSave = new Supplier(1, "Lemonades", "contact@lemonades.com");
	supplierRepository.save(firstSupplierToSave);
	Supplier secondSupplierToSave = new Supplier(2, "Water", "contact@water.com");
	supplierRepository.save(secondSupplierToSave);

	Supplier firstSupplier = supplierRepository.findById(1);
	Supplier secondSupplier = supplierRepository.findById(2);

	assert firstSupplier.getId() == 1;
	assert secondSupplier.getId() == 2;

    }

    public void testAllRepository() {
	shouldSaveOneElement_whenSaveIsCalled();
	shouldSaveTwoElements_whenSaveIsCalledTwice();
	shouldUpdateSupplier_whenUpdateMethodCalled();
	shouldDeleteSupplier_whenDeletedMethodIsCalled();
	shouldFindSupplier_whenFindMethodCalled();
    }
}
