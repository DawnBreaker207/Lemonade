package test.com.explainjava.repository;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import main.java.com.explainjava.domain.Supplier;
import main.java.com.explainjava.exceptions.IDNotUniqueException;
import main.java.com.explainjava.repository.SupplierFileRepository;

public class SupplierFileRepositoryTest {
    private String filename;

    public SupplierFileRepositoryTest(String filename) {
	this.filename = filename;
    }

    private void clearFile() {
	try (FileOutputStream fos = new FileOutputStream(filename)) {

	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    public void shouldSaveToFileOneElement_whenSaveIsCalled() throws IDNotUniqueException {
	clearFile();
	SupplierFileRepository fileRepository = new SupplierFileRepository(filename);
	Supplier firstSupplierToSave = new Supplier(1, "Lemonades", "contact@lemonades.com");

	Supplier FirstSavedSupplier = fileRepository.save(firstSupplierToSave);

	List<Supplier> suppliersFromFile = fileRepository.readSupliersFromFile();

	assert suppliersFromFile.size() == 1;
	assert fileRepository.findById(1) != null;
	clearFile();
    }

    public void testAllSupplierFileRepository() throws IDNotUniqueException {
	shouldSaveToFileOneElement_whenSaveIsCalled();
    }
}
