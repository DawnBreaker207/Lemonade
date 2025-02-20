package main.java.com.explainjava.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import main.java.com.explainjava.domain.Supplier;
import main.java.com.explainjava.exceptions.ValidationException;

public class SupplierValidator {
    public void validateSupplier(Supplier supplier) throws ValidationException {
	StringBuilder stringBuilder = new StringBuilder();
	if (supplier.getName().length() < 3 || supplier.getName().length() > 100) {
	    stringBuilder.append("The name should betwwen 3 and 100 chars long. \n");
	}

	String regex = "^(.+)@(.+)$";
	Pattern pattern = Pattern.compile(regex);
	Matcher matcher = pattern.matcher(supplier.getContactEmail());

	if (supplier.getContactEmail().length() < 3 || supplier.getContactEmail().length() > 100) {
	    stringBuilder.append("The contact email should be between 3 and 100 chars long. \n");
	}

	if (!matcher.matches()) {
	    stringBuilder.append("The contact email should have this pattern: email@email.com \n");
	}

	if (!stringBuilder.isEmpty()) {
	    throw new ValidationException(stringBuilder.toString());
	}
    }
}
