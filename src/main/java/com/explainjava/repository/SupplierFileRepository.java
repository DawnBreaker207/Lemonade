package main.java.com.explainjava.repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import main.java.com.explainjava.domain.Supplier;
import main.java.com.explainjava.exceptions.IDNotUniqueException;

public class SupplierFileRepository extends GenericRepository<Supplier> {
    private String filename;

    public List<Supplier> readSupliersFromFile() {
	List<Supplier> suppliers = new ArrayList<>();
	BufferedReader reader = null;
	try {
	    reader = new BufferedReader(new FileReader(filename));
	    String line;
	    while ((line = reader.readLine()) != null) {
		String[] elems = line.split(",");
//		String[] elems = line.split("\\|");

		int id = Integer.parseInt(elems[0]);
		String name = elems[1];
		String contactEmail = elems[2];

		Supplier supplier = new Supplier(id, name, contactEmail);
		suppliers.add(supplier);
	    }
	    reader.close();
	} catch (IOException e) {
	    throw new RuntimeException(e);
	}
	return suppliers;
    }

    public SupplierFileRepository(String filename) throws IDNotUniqueException {
	super();
	this.filename = filename;
	loadSuppliersFromFile();
    }

    private void loadSuppliersFromFile() throws IDNotUniqueException {
	List<Supplier> suppliers = readSupliersFromFile();
	for (Supplier supplier : suppliers) {
	    this.save(supplier);
	}
    }

    private void writeToFile() {
	BufferedWriter writer = null;
	try {
	    writer = new BufferedWriter(new FileWriter(filename));
	    Iterable<Supplier> suppliers = findAll();

	    for (Supplier supplier : suppliers) {
		String line = supplier.getId() + "," + supplier.getName() + "," + supplier.getContactEmail();
		writer.write(line);
		writer.newLine();
	    }
	    writer.close();
	} catch (IOException e) {
	    throw new RuntimeException(e);
	}
    }

    @Override
    public Supplier save(Supplier supplier) throws IDNotUniqueException {
	Supplier savedSupplier = super.save(supplier);
	writeToFile();
	return savedSupplier;
    }

    @Override
    public Supplier update(Supplier supplier) {
	Supplier updatedSupplier = super.update(supplier);
	writeToFile();
	return updatedSupplier;
    }

    @Override
    public void delete(int supplierId) {
	super.delete(supplierId);
	writeToFile();
    }
}
