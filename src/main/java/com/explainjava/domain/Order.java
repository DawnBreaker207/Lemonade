package main.java.com.explainjava.domain;

import java.util.Date;

public class Order extends Entity {
    private Lemonade lemonade;

    private int quantity;

    private int finalPrice;

    private Date date;

    public Order(int id, Lemonade lemonade, int quantity, int finalPrice, Date date) {
	super.id = id;
	this.lemonade = lemonade;
	this.quantity = quantity;
	this.finalPrice = finalPrice;
	this.date = date;
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

    public int getFinalPrice() {
	return finalPrice;
    }

    public void setFinalPrice(int finalPrice) {
	this.finalPrice = finalPrice;
    }

    public Date getDate() {
	return date;
    }

    public void setDate(Date date) {
	this.date = date;
    }
}
