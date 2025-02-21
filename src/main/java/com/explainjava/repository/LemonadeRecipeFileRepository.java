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

public class LemonadeRecipeFileRepository extends GenericRepository<LemonadeRecipe> {
    private String filename;

    public LemonadeRecipeFileRepository(String filename) throws IDNotUniqueException {
	super();
	this.filename = filename;
	loadLemonadesFromFile();

    }

    @Override
    public LemonadeRecipe save(LemonadeRecipe lemonadeecipe) throws IDNotUniqueException {
	LemonadeRecipe savedLemonadeRecipe = super.save(lemonadeecipe);
	writeToFile();
	return savedLemonadeRecipe;
    }

    @Override
    public LemonadeRecipe update(LemonadeRecipe lemonade) {
	LemonadeRecipe savedLemonade = super.update(lemonade);
	writeToFile();
	return savedLemonade;
    }

    @Override
    public void delete(int lemonadeId) {
	super.delete(lemonadeId);
	writeToFile();
    }

    private void loadLemonadesFromFile() throws IDNotUniqueException {
	List<LemonadeRecipe> lemonadeRecipes = readLemonadesRecipesFromFile();
	for (LemonadeRecipe lemonadeRecipe : lemonadeRecipes) {
	    this.save(lemonadeRecipe);
	}
    }

    public List<LemonadeRecipe> readLemonadesRecipesFromFile() {
	List<LemonadeRecipe> lemonadeRecipes = new ArrayList<>();
	BufferedReader reader = null;

	try {
	    reader = new BufferedReader(new FileReader(filename));
	    String line;
	    while ((line = reader.readLine()) != null) {
		String[] elems = line.split(",");

		int id = Integer.parseInt(elems[0]);
		int productId = Integer.parseInt(elems[1]);
		int lemonadeId = Integer.parseInt(elems[2]);
		int quantity = Integer.parseInt(elems[3]);

		Product product = new Product(productId);
		Lemonade lemonade = new Lemonade(lemonadeId);

		LemonadeRecipe lemonadeRecipe = new LemonadeRecipe(id, product, lemonade, quantity);
		lemonadeRecipe.add(LemonadeRecipe);
	    }
	    reader.close();
	} catch (IOException e) {
	    throw new RuntimeException(e);
	}

	return lemonadeRecipes;
    }

    private void writeToFile() {
	BufferedWriter writer = null;
	try {

	    writer = new BufferedWriter(new FileWriter(filename));

	    Iterable<LemonadeRecipe> lemonadeRecipes = findAll();
	    for (LemonadeRecipe lemonadeRecipe : lemonadeRecipes) {
		String line = lemonadeRecipe.getId() + "," + lemonadeRecipe.getProduct().getId() + ","
			+ lemonadeRecipe.getLemonade().getId() + "," + lemonadeRecipe.getQuantity();
		writer.write(line);
		writer.newLine();
	    }
	    writer.close();
	} catch (IOException e) {
	    throw new RuntimeException(e);
	}
    }
}
