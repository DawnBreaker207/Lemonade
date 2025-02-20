package main.java.com.explainjava;

import main.java.com.explainjava.exceptions.IDNotUniqueException;
import main.java.com.explainjava.exceptions.ValidationException;
import main.java.com.explainjava.repository.SupplierFileRepository;
import main.java.com.explainjava.service.SupplierService;
import main.java.com.explainjava.userinterface.UserInterface;
import main.java.com.explainjava.validators.SupplierValidator;

public class Main {

    public static void main(String[] args) throws ValidationException, IDNotUniqueException {
	SupplierFileRepository supplierRepository = new SupplierFileRepository("suppliers.csv");
//	SupplierRepository supplierRepository = new SupplierRepository();
	SupplierValidator supplierValidator = new SupplierValidator();
	SupplierService supplierService = new SupplierService(supplierRepository, supplierValidator);
	UserInterface userInterface = new UserInterface(supplierService);

	userInterface.runMenu();
    }

}
