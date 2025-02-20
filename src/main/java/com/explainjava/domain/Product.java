package main.java.com.explainjava.domain;

public class Product {
    private int id;
    private String name;
    private String description;
    private int price;
    private int quantity;
    private Supplier supplier;

    public Product(int id, String name, String description, int price, int quantity, Supplier supplier) {
	this.id = id;
	this.name = name;
	this.description = description;
	this.price = price;
	this.quantity = quantity;
	this.supplier = supplier;
    }

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public int getPrice() {
	return price;
    }

    public void setPrice(int price) {
	this.price = price;
    }

    public int getQuantity() {
	return quantity;
    }

    public void setQuantity(int quantity) {
	this.quantity = quantity;
    }

    public Supplier getSupplier() {
	return supplier;
    }

    public void setSupplier(Supplier supplier) {
	this.supplier = supplier;
    }

    @Override
    public String toString() {
	return "Product{" + "id=" + id + ", name='" + name + '\'' + ", description='" + description + '\'' + ", price="
		+ price + ", quantity=" + quantity + '}';
    }
}
