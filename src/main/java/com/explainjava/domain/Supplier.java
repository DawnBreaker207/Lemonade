package main.java.com.explainjava.domain;

public class Supplier {
    private static int id;
    private String name;
    private String contactEmail;

    public int getId() {
//	int newId = id;
//	id++;
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

    public String getContactEmail() {
	return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
	this.contactEmail = contactEmail;
    }

    public Supplier() {
	this.id = -1;
    }

    public Supplier(Integer id, String name, String contactEmail) {
	this.id = id;
	this.name = name;
	this.contactEmail = contactEmail;
    }

    @Override
    public String toString() {
	return "Supplier{" + "id=" + id + ", name='" + name + '\'' + ", email='" + contactEmail + '\'' + '}';
    }
}
