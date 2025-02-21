package main.java.com.explainjava.domain;

public class Lemonade extends Entity {
    private String name;
    private int totalPrice;

    public Lemonade(int id, String name, int totalPrice) {
	this.id = id;
	this.name = name;
	this.totalPrice = totalPrice;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public int getTotalPrice() {
	return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
	this.totalPrice = totalPrice;
    }
}
