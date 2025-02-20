package main.java.com.explainjava.service;

import main.java.com.explainjava.domain.Product;
import main.java.com.explainjava.domain.Supplier;
import main.java.com.explainjava.exceptions.IDNotUniqueException;
import main.java.com.explainjava.exceptions.ValidationException;
import main.java.com.explainjava.repository.ProductRepository;
import main.java.com.explainjava.validators.ProductValidator;

public class ProductService {
    private ProductRepository productRepository;
    private ProductValidator productValidator;
    private SupplierService supplierService;

    public ProductService(ProductRepository productRepository, ProductValidator productValidator,
	    SupplierService supplierService) {
	this.productRepository = productRepository;
	this.productValidator = productValidator;
	this.supplierService = supplierService;
    }

    public Product saveProduct(int id, String name, String description, int price, int quantity, int supplierId)
	    throws ValidationException, IDNotUniqueException {
	Supplier supplier = supplierService.findById(supplierId);
	Product product = new Product(id, name, description, price, quantity, supplier);

	productValidator.validateProduct(product);
	Product savedProduct = productRepository.save(product);
	return savedProduct;
    }

    public void removeProduct(int id) {
	productRepository.delete(id);
    }

    public Product updateProduct(int id, String newName, String newDescription, int newPrice, int newQuantity,
	    int newSupplierId) throws ValidationException, IDNotUniqueException {
	Supplier supplier = supplierService.findById(newSupplierId);
	Product productToUpdate = new Product(id, newName, newDescription, newPrice, newQuantity, supplier);

	productValidator.validateProduct(productToUpdate);
	Product updatedProduct = productRepository.update(productToUpdate);
	return updatedProduct;
    }

    public Iterable<Product> getAll() {
	return productRepository.findAll();
    }
}
