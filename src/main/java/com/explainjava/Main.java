package main.java.com.explainjava;

import main.java.com.explainjava.repository.SupplierRepository;
import main.java.com.explainjava.service.SupplierService;
import main.java.com.explainjava.userinterface.UserInterface;

public class Main {

    public static void main(String[] args) {
	SupplierRepository supplierRepository = new SupplierRepository();
	SupplierService supplierService = new SupplierService(supplierRepository);
	UserInterface userInterface = new UserInterface(supplierService);
	
	
	userInterface.runMenu();
    }

}
