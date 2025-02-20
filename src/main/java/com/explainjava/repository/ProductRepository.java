package main.java.com.explainjava.repository;

import java.util.HashMap;
import java.util.Map;

import main.java.com.explainjava.domain.Product;
import main.java.com.explainjava.exceptions.IDNotUniqueException;

public class ProductRepository {
    private Map<Integer, Product> products;

    public ProductRepository() {
	products = new HashMap<>();
    }

    public Product save(Product product) throws IDNotUniqueException {
	if (products.containsKey(product.getId())) {
	    throw new IDNotUniqueException("The id is not unique");
	}
	products.put(product.getId(), product);
	return product;
    }

    public Product update(Product product) {
	if (products.containsKey(product.getId())) {
	    products.put(product.getId(), product);
	}
	return product;
    }

    public void delete(int productId) {
	products.remove(productId);
    }

    public Iterable<Product> findAll() {
	return products.values();
    }

    public Product findById(int productId) {
	return products.get(productId);
    }
}
