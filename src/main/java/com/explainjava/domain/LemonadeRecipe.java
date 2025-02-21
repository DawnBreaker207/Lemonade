package main.java.com.explainjava.domain;

public class LemonadeRecipe extends Entity {
    private Product product;
    private Lemonade lemonade;
    private int quantity;

    public LemonadeRecipe(int id, Product product, Lemonade lemonade, int quantity) {
	this.id = id;
	this.product = product;
	this.lemonade = lemonade;
	this.quantity = quantity;
    }

    public Product getProduct() {
	return product;
    }

    public void setProduct(Product product) {
	this.product = product;
    }

    public Lemonade getLemonade() {
	return lemonade;
    }

    public void setLemonade(Lemonade lemonade) {
	this.lemonade = lemonade;
    }

    public int getQuantity() {
	return quantity;
    }

    public void setQuantity(int quantity) {
	this.quantity = quantity;
    }
}
