package main.java.com.explainjava.userinterface;

import java.util.Scanner;

import main.java.com.explainjava.domain.Product;
import main.java.com.explainjava.domain.Supplier;
import main.java.com.explainjava.exceptions.IDNotUniqueException;
import main.java.com.explainjava.exceptions.ValidationException;
import main.java.com.explainjava.service.LemonadeService;
import main.java.com.explainjava.service.OrderService;
import main.java.com.explainjava.service.ProductService;
import main.java.com.explainjava.service.SupplierService;

public class UserInterface {
    private ProductService productService;
    private SupplierService supplierService;
    private LemonadeService lemonadeService;
    private OrderService orderService;

    private Scanner scanner = new Scanner(System.in);

    public UserInterface(ProductService productService, SupplierService supplierService,
	    LemonadeService lemonadeService, OrderService orderService) {
	this.productService = productService;
	this.supplierService = supplierService;
	this.lemonadeService = lemonadeService;
	this.orderService = orderService;
    }

    private void showMenu() {
	System.out.println("Welcome to the Lemonade Stand Administration App.");
	System.out.println("The Menu:");
	System.out.println("1. Manage suppliers");
	System.out.println("2. Manage products");
	System.out.println("3. Manage lemonade recipes");
	System.out.println("4. Create an order");
	System.out.println("5. Daily sales report");
	System.out.println("6. Empty products stock report");
	System.out.println("7. Exit");
	System.out.println("What do you want to do ?");
    }

    private void showSuppliersMenu() {
	System.out.println("Welcome to the Lemonade Stand Administration App.");
	System.out.println("The Suppliers menu:");
	System.out.println("1. Add a supplier");
	System.out.println("2. Update a supplier");
	System.out.println("3. Remove a supplier");
	System.out.println("4. Display all suppliers");
	System.out.println("5. Back to main menu");
	System.out.println("What do you want to do ?");
    }

    private void showProductsMenu() {
	System.out.println("Welcome to the Lemonade Stand Administration App.");
	System.out.println("The Product menu:");
	System.out.println("1. Add a product");
	System.out.println("2. Update a product");
	System.out.println("3. Remove a product");
	System.out.println("4. Display all products");
	System.out.println("5. Back to main menu");
	System.out.println("What do you want to do ?");
    }

    public void runMenu() {
	Scanner scanner = new Scanner(System.in);
	int option = -1;

	while (option != 7) {
	    showMenu();

	    option = scanner.nextInt();

	    switch (option) {

	    case 1:
		runSuppliersMenu(scanner);
		break;
	    case 2:
		runProductsMenu(scanner);
		break;

	    case 3, 4, 5, 6:
		System.out.println("Not implemented yet!");
		break;
	    case 7:
		break;
	    }
	}
	scanner.close();
    }

    public void runSuppliersMenu(Scanner scanner) {
	int option = -1;

	while (option != 5) {
	    showSuppliersMenu();
	    option = scanner.nextInt();

	    switch (option) {

	    case 1:
		handleAddSupplier(scanner);
		break;
	    case 2:
		handleRemoveSuppliers(scanner);
		break;
	    case 3:
		handleUpdateSupplier(scanner);
		break;
	    case 4:
		handleShowSuppliers();
		break;
	    case 5:

		break;
	    }
	}
	scanner.close();
    }

    public void runProductsMenu(Scanner scanner) {
	int option = -1;

	while (option != 5) {
	    showProductsMenu();
	    option = scanner.nextInt();

	    switch (option) {

	    case 1:
		handleAddProduct(scanner);
		break;
	    case 2:
		handleRemoveProducts(scanner);
		break;
	    case 3:
		handleUpdateProduct(scanner);
		break;
	    case 4:
		handleShowProducts();
		break;
	    case 5:

		break;
	    }
	}
	scanner.close();
    }

    private void handleAddSupplier(Scanner scanner) {
	System.out.print("ID: ");
	int id = scanner.nextInt();

	System.out.print("Name: ");
	String name = scanner.next();

	System.out.print("Contact email: ");
	String contactEmail = scanner.next();

	try {
	    Supplier savedSupplier = supplierService.saveSupplier(id, name, contactEmail);
	    System.out.printf("The supplier with ID=%s has been saved \n", savedSupplier.getId());
	} catch (ValidationException | IDNotUniqueException e) {
	    System.out.println("Error with saving the supplier " + e.getMessage());
	}
    }

    private void handleUpdateSupplier(Scanner scanner) {
	System.out.print("The ID of the supplier to be updated: ");
	int id = scanner.nextInt();

	System.out.print("New name: ");
	String name = scanner.next();

	System.out.print("New contact email: ");
	String contactEmail = scanner.next();

	Supplier updatedSupplier = supplierService.updateSupplier(id, name, contactEmail);
	System.out.printf("The supplier with ID=%s has been updated \n", updatedSupplier.getId());
    }

    private void handleRemoveSuppliers(Scanner scanner) {
	System.out.print("The ID of the supplier to be removed: ");
	int supplierIdToRemove = scanner.nextInt();

	supplierService.removeSupplier(supplierIdToRemove);
	System.out.printf("The product with ID=%s has been removed \n", supplierIdToRemove);
    }

    private void handleShowSuppliers() {
	Iterable<Supplier> supplierList = supplierService.findAll();
	displaySuppliers(supplierList);
    }

    private void displaySuppliers(Iterable<Supplier> suppliers) {
	for (Supplier supplier : suppliers) {
	    System.out.println(supplier);
	}
    }

    private void handleAddProduct(Scanner scanner) {
	System.out.print("ID: ");
	int id = scanner.nextInt();

	System.out.print("Name: ");
	String name = scanner.next();

	System.out.print("Description: ");
	String description = scanner.next();

	System.out.print("Price: ");
	int price = scanner.nextInt();

	System.out.print("Quantity: ");
	int quantity = scanner.nextInt();

	System.out.print("Supplier id: ");
	int supplierId = scanner.nextInt();

	try {
	    productService.saveProduct(id, name, description, price, quantity, supplierId);

	} catch (ValidationException | IDNotUniqueException e) {
	    System.out.println("Error with saving the supplier " + e.getMessage());
	}
    }

    private void handleUpdateProduct(Scanner scanner) {
	System.out.print("The ID of the product to be updated: ");
	int id = scanner.nextInt();

	System.out.print("New name: ");
	String name = scanner.next();

	System.out.print("New description: ");
	String description = scanner.next();

	System.out.print("New price: ");
	int price = scanner.nextInt();

	System.out.print("New quantity: ");
	int quantity = scanner.nextInt();

	System.out.print("Supplier id: ");
	int supplierId = scanner.nextInt();

	try {
	    productService.updateProduct(id, name, description, price, quantity, supplierId);
	} catch (ValidationException | IDNotUniqueException e) {
	    System.out.println("Error with saving the product " + e.getMessage());
	}
	;
    }

    private void handleRemoveProducts(Scanner scanner) {
	System.out.print("The ID of the product to be removed: ");
	int id = scanner.nextInt();

	productService.removeProduct(id);
    }

    private void handleShowProducts() {
	Iterable<Product> productList = productService.getAll();
	for (Product product : productList) {
	    System.out.println(product);
	}
    }

}
