package main.java.com.explainjava.domain;

public class Supplier extends Entity {
    private static int id;
    private String name;
//    private SupplierType type;
    private String contactEmail;

    public Supplier() {

    }

    public Supplier(int id, String name, 
//	    SupplierType type,
	    String contactEmail) {
	super.id = id;
	this.name = name;
//	this.type = type;
	this.contactEmail = contactEmail;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

//    public SupplierType getType() {
//	return type;
//    }
//
//    public void setType(SupplierType type) {
//	this.type = type;
//    }

    public String getContactEmail() {
	return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
	this.contactEmail = contactEmail;
    }

    @Override
    public String toString() {
	return "Supplier{" + "id=" + id + ", name='" + name + '\'' 
//		+ ", type=" + type 
		+ ", contactEmail='"
		+ contactEmail + '\'' + '}';
    }
}
