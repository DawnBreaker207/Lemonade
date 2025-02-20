package test.com.explainjava.domain;

import main.java.com.explainjava.domain.Supplier;

public class SupplierTest {
    public void shouldGetCorrectValues_whenContructorIsCalled() {
	Supplier supplier = new Supplier(1, "Lemonades", "contact@lemonades.com");

	assert supplier.getId() == 1;
	assert supplier.getName().equals("Lemonades");
	assert supplier.getContactEmail().equals("contact@lemonades.com");
    }

    public void shouldSetCorrectValues_whenSettersAreUsed() {
	Supplier supplier = new Supplier(1, "Lemonades", "contact@lemonades.com");

	supplier.setId(2);
	supplier.setName("Lemonades name");
	supplier.setContactEmail("contactlemonades@lemonades.com");

	assert supplier.getId() == 1;
	assert supplier.getName().equals("Lemonades name");
	assert supplier.getContactEmail().equals("contactlemonades@lemonades.com");
    }

    public void testAllDomain() {
	shouldGetCorrectValues_whenContructorIsCalled();
	shouldSetCorrectValues_whenSettersAreUsed();
    }
}
