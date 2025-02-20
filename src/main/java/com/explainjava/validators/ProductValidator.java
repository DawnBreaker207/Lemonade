package main.java.com.explainjava.validators;

import main.java.com.explainjava.domain.Product;
import main.java.com.explainjava.exceptions.ValidationException;

public class ProductValidator {
    public void validateProduct(Product product) throws ValidationException {
	StringBuilder stringBuilder = new StringBuilder();
	if (product.getName().length() < 3 || product.getName().length() > 100) {
	    stringBuilder.append("The name should be between 3 and 100 chars. \n");
	}

	if (product.getDescription().length() < 3 || product.getDescription().length() > 250) {
	    stringBuilder.append("The description should be between 3 and 100 chars. \n");
	}

	if (product.getPrice() < 1 || product.getPrice() > 1000) {
	    stringBuilder.append("The price should be between 1 and 1000. \n");
	}

	if (product.getQuantity() < 0 || product.getQuantity() > 25) {
	    stringBuilder.append("The quantity should be between 1 and 25. \n");
	}

	if (!stringBuilder.isEmpty()) {
	    throw new ValidationException(stringBuilder.toString());
	}
    }
}
