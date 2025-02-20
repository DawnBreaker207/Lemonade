package test.com.explainjava;

import main.java.com.explainjava.domain.Supplier;
import main.java.com.explainjava.exceptions.IDNotUniqueException;
import main.java.com.explainjava.exceptions.ValidationException;
import main.java.com.explainjava.repository.SupplierRepository;
import main.java.com.explainjava.service.SupplierService;
import main.java.com.explainjava.userinterface.UserInterface;
import main.java.com.explainjava.validators.SupplierValidator;
import test.com.explainjava.domain.SupplierTest;
import test.com.explainjava.repository.SupplierFileRepositoryTest;
import test.com.explainjava.repository.SupplierRepositoryTest;
import test.com.explainjava.service.SupplierServiceTest;

public class MainTest {

    public static void main(String[] args) {
	SupplierRepository supplierRepository = new SupplierRepository();
	SupplierValidator supplierValidator = new SupplierValidator();
	SupplierService supplierService = new SupplierService(supplierRepository, supplierValidator);
	UserInterface userInterface = new UserInterface(supplierService);

	MainTest test = new MainTest();
	test.runAllTest();
	userInterface.runMenu();

    }

    public void runAllTest() {
	try {
	    SupplierTest domainTests = new SupplierTest();
	    domainTests.testAllDomain();

	    SupplierRepositoryTest repositoryTests = new SupplierRepositoryTest();
	    repositoryTests.testAllRepository();

	    SupplierFileRepositoryTest repoFileRepositoryTests = new SupplierFileRepositoryTest("test-file.csv");
	    repoFileRepositoryTests.testAllSupplierFileRepository();

	    SupplierServiceTest serviceTests = new SupplierServiceTest();
	    serviceTests.testAllService();
	    System.out.println("All tests have run successfully!");

	} catch (ValidationException | IDNotUniqueException e) {
	    System.out.println("The tests have failed, e=" + e.getMessage());
	}
    }

    public void shouldNotSaveTheElement_whenWeAddNotUniqueElement() throws IDNotUniqueException {
	SupplierRepository supplierRepository = new SupplierRepository();
	Supplier firstSupplierToSave = new Supplier(1, "Lemonades", "contact@lemonades.com");
	Supplier secondSupplierToSave = new Supplier(1, "Lemonades", "contact@lemonades.com");
	try {
	    Supplier firstSavedSupplier = supplierRepository.save(firstSupplierToSave);
	    Supplier secondSavedSupplier = supplierRepository.save(secondSupplierToSave);
	    assert false;
	} catch (IDNotUniqueException e) {
	    assert true;
	}
    }
}
