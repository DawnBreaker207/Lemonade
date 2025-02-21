package test.com.explainjava.service;

import main.java.com.explainjava.domain.Product;
import main.java.com.explainjava.domain.Supplier;
import main.java.com.explainjava.exceptions.IDNotUniqueException;
import main.java.com.explainjava.exceptions.ValidationException;
import main.java.com.explainjava.repository.ProductRepository;
import main.java.com.explainjava.repository.SupplierRepository;
import main.java.com.explainjava.service.ProductService;
import main.java.com.explainjava.service.SupplierService;
import main.java.com.explainjava.validators.ProductValidator;
import main.java.com.explainjava.validators.SupplierValidator;

public class ProductServiceTest {
    private SupplierService supplierService;
    private ProductService productService;

    private void setUp() {
	SupplierRepository supplierRepository = new SupplierRepository();
	SupplierValidator supplierValidator = new SupplierValidator();
	supplierService = new SupplierService(supplierRepository, supplierValidator);

	ProductRepository productFileRepository = new ProductRepository();
	ProductValidator productValidator = new ProductValidator();
	productService = new ProductService(productFileRepository, productValidator, supplierService);
    }

    public void shouldSaveProduct_whenSavedMethodCalled() throws ValidationException, IDNotUniqueException {
	setUp();

	Supplier supplier = supplierService.saveSupplier(1, "Sugar supplier", "supplier@email.com");
	Product savedProduct = productService.saveProduct(1, "Sugar", "Sweet sugar", 10, 10, supplier.getId());

	assert savedProduct != null;
	assert savedProduct.getId() == 1;
	assert savedProduct.getName().equals("Sugar");
    }

    public void shouldUpdateProduct_whenUpdateMethoCalled() throws ValidationException, IDNotUniqueException {
	setUp();

	Supplier supplier = supplierService.saveSupplier(1, "Sugar supplier", "supplier@email.com");
	Product savedProduct = productService.saveProduct(1, "Sugar", "Sweet sugar", 10, 10, supplier.getId());
	Product updatedProduct = productService.saveProduct(1, "Sugar", "Brown sugar", 10, 10, supplier.getId());

	assert savedProduct != null;
	assert savedProduct.getId() == 1;
	assert updatedProduct.getDescription().equals("Brown sugar");
    }

    public void shouldRemoveProduct_whenRemoveMethodCalled() throws ValidationException, IDNotUniqueException {
	setUp();

	Supplier supplier = supplierService.saveSupplier(1, "Sugar supplier", "supplier@email.com");
	Product savedProduct = productService.saveProduct(1, "Sugar", "Sweet sugar", 10, 10, supplier.getId());

	assert !productService.getAll().iterator().hasNext();
    }

    public void testAllProductService() throws ValidationException, IDNotUniqueException {
	shouldSaveProduct_whenSavedMethodCalled();
	shouldUpdateProduct_whenUpdateMethoCalled();
	shouldRemoveProduct_whenRemoveMethodCalled();
    }
}
