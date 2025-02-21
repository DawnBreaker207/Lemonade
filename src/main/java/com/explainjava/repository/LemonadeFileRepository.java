package main.java.com.explainjava.repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import main.java.com.explainjava.domain.Lemonade;
import main.java.com.explainjava.domain.LemonadeRecipe;
import main.java.com.explainjava.domain.Product;
import main.java.com.explainjava.exceptions.IDNotUniqueException;

public class LemonadeFileRepository extends GenericRepository<Lemonade> {
    private String filename;

    public LemonadeFileRepository(String filename) throws IDNotUniqueException {
	super();
	this.filename = filename;
	loadLemonadesFromFile();

    }

    @Override
    public Lemonade save(Lemonade lemonade) throws IDNotUniqueException {
	Lemonade savedLemonade = super.save(lemonade);
	writeToFile();
	return savedLemonade;
    }

    @Override
    public Lemonade update(Lemonade lemonade) {
	Lemonade savedLemonade = super.update(lemonade);
	writeToFile();
	return savedLemonade;
    }

    @Override
    public void delete(int lemonadeId) {
	super.delete(lemonadeId);
	writeToFile();
    }

    private void loadLemonadesFromFile() throws IDNotUniqueException {
	List<Lemonade> lemonades = readLemonadesFromFile();
	for (Lemonade lemonade : lemonades) {
	    this.save(lemonade);
	}
    }

    public List<Lemonade> readLemonadesFromFile() {
	List<Lemonade> lemonades = new ArrayList<>();
	BufferedReader reader = null;

	try {
	    reader = new BufferedReader(new FileReader(filename));
	    String line;
	    while ((line = reader.readLine()) != null) {
		String[] elems = line.split(",");

		int id = Integer.parseInt(elems[0]);
		String name = elems[1];
		int totalPrice = Integer.parseInt(elems[2]);

		Lemonade lemonade = new Lemonade(id, name, totalPrice);
		lemonades.add(lemonade);
	    }
	    reader.close();
	} catch (IOException e) {
	    throw new RuntimeException(e);
	}

	return lemonades;
    }

    private void writeToFile() {
	BufferedWriter writer = null;
	try {

	    writer = new BufferedWriter(new FileWriter(filename));

	    Iterable<Lemonade> lemonades = findAll();
	    for (Lemonade lemonade : lemonades) {
		String line = lemonade.getId() + "," + lemonade.getName() + "," + lemonade.getTotalPrice();
		writer.write(line);
		writer.newLine();
	    }
	    writer.close();
	} catch (IOException e) {
	    throw new RuntimeException(e);
	}
    }
}
