package main.java.com.explainjava.domain;

public class Lemonade extends Entity {
    private String name;
    private String totalPrice;

    public Lemonade(String name) {
	this.name = name;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getTotalPrice() {
	return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
	this.totalPrice = totalPrice;
    }
}
