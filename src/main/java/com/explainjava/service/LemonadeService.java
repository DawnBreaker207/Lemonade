package main.java.com.explainjava.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import main.java.com.explainjava.domain.Lemonade;
import main.java.com.explainjava.domain.LemonadeRecipe;
import main.java.com.explainjava.domain.Product;
import main.java.com.explainjava.repository.RepositoryInterface;

public class LemonadeService {
    private RepositoryInterface<LemonadeRecipe> lemonadeRecipeRepository;
    private RepositoryInterface<Lemonade> lemonadeRepository;
    private ProductService productService;

    public LemonadeService(RepositoryInterface lemonadeRecipeRepository, RepositoryInterface lemonadeRepository,
	    ProductService productService) {
	this.lemonadeRepository = lemonadeRepository;
	this.lemonadeRecipeRepository = lemonadeRecipeRepository;
	this.productService = productService;
    }

    public Lemonade findById(int lemonadeId) {
	return lemonadeRepository.findById(lemonadeId);
    }

    public Iterable<Lemonade> findAll() {
	return lemonadeRepository.findAll();
    }

    public List<LemonadeRecipe> findLemonadeRecipe(int lemonadeId) {
	Iterable<LemonadeRecipe> allLemonadeRecipes = lemonadeRecipeRepository.findAll();
	List<LemonadeRecipe> recipeForTheRequestedLemonade = new ArrayList<>();

	for (LemonadeRecipe lemonadeRecipe : allLemonadeRecipes) {
	    if (lemonadeRecipe.getLemonade().getId() == lemonadeId) {
		Product loadedProduct = productService.findById(lemonadeRecipe.getProduct().getId());
		lemonadeRecipe.setProduct(loadedProduct);

		recipeForTheRequestedLemonade.add(lemonadeRecipe);
	    }
	}
	return recipeForTheRequestedLemonade;
    }
}
