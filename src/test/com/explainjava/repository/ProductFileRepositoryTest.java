package test.com.explainjava.repository;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import main.java.com.explainjava.domain.Product;
import main.java.com.explainjava.domain.Supplier;
import main.java.com.explainjava.exceptions.IDNotUniqueException;
import main.java.com.explainjava.repository.ProductFileRepository;

public class ProductFileRepositoryTest {
    private String filename;

    public ProductFileRepositoryTest(String filename) {
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
	ProductFileRepositoryTest

	fileRepository = new ProductFileRepository(filename);

	Supplier supplier = new Supplier(1, "Sugar supplier", "supplier@email.com");
	Product firstProductToSave = new Product(1, "Sugar", "Sweet sugar", 10, 10, supplier);

	Product firstSavedSupplier = fileRepository.save(firstProductToSave);

	List<Product> products = fileRepository.readProductFromFile();
	Product savedProduct = fileRepository.findByid(1);

	assert products.size() == 1;
	assert savedProduct != null;
	assert savedProduct.getSupplier() != null;
	clearFile();
    }

    public void testAllProductFileRepository() throws IDNotUniqueException {
	shouldSaveToFileOneElement_whenSaveIsCalled();
    }
}
