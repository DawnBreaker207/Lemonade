package main.java.com.explainjava.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import main.java.com.explainjava.domain.Lemonade;
import main.java.com.explainjava.domain.LemonadeRecipe;
import main.java.com.explainjava.domain.Order;
import main.java.com.explainjava.domain.Product;
import main.java.com.explainjava.dots.DailySalesDTO;
import main.java.com.explainjava.exceptions.ValidationException;
import main.java.com.explainjava.repository.RepositoryInterface;

public class OrderService {
    private RepositoryInterface<Order> orderRepository;
    private LemonadeService lemonadeService;
    private ProductService productService;

    public OrderService(RepositoryInterface orderRepository, LemonadeService lemonadeService,
	    ProductService productService) {
	this.orderRepository = orderRepository;
	this.lemonadeService = lemonadeService;
	this.productService = productService;
    }

    public Order saveOrder(int id, int lemonadeId, int orderQuantity) throws ValidationException, IDNotUniqueException {
	Lemonade lemonade = lemonadeService.findById(lemonadeId);
	List<LemonadeRecipe> recipe = lemonadeService.findLemonadeRecipe(lemonadeId);

	boolean isEnoughStock = isEnoughStockForOrder(recipe, orderQuantity);
	if (isEnoughStock) {
	    updateProductStock(recipe, orderQuantity);
	    Order order = new Order(id, lemonade, orderQuantity, orderQuantity * lemonade.getTotalPrice(), new Date());
	    Order savedOrder = orderRepository.save(order);

	    return savedOrder;
	} else {
	    throw new ValidationException("Not enough stock for the order");
	}
    }

    private void updateProductStock(List<LemonadeRecipe> recipe, int orderQuantity) throws ValidationException {
	for (LemonadeRecipe lemonadeRecipe : recipe) {
	    Product product = productService.findById(lemonadeRecipe.getProduct().getId());
	    int totalQuantity = lemonadeRecipe.getQuantity() * orderQuantity;

	    if (totalQuantity <= product.getQuantity()) {
		product.setQuantity(product.getQuantity() - totalQuantity);
		productService.updateProduct(product.getId(), product.getName(), product.getDescription(),
			product.getPrice(), product.getQuantity(), product.getSupplier().getId());
	    }
	}
    }

    private boolean isEnoughStockForOrder(List<LemonadeRecipe> recipe, int orderQuantity) {
	boolean isValid = true;
	for (LemonadeRecipe lemonadeRecipe : recipe) {
	    Product product = productService.findById(lemonadeRecipe.getProduct().getId());
	    int totalQuantity = lemonadeRecipe.getQuantity() * orderQuantity;

	    if (product.getQuantity() < totalQuantity) {
		isValid = false;
	    }
	}

	return isValid;
    }

    public List<DailySalesDTO> getDailyReport() {
	List<DailySalesDTO> report = new ArrayList()<>();
	Iterable<Order> orders = orderRepository.findAll();

	for (Order order : orders) {
	    Date date = order.getDate();
	    boolean alreadyAddedDay = false;
	    for (DailySalesDTO day : report) {
		if (day.getDay().equals(date)) {
		    alreadyAddedDay = true;
		    day.setTotalSales(day.getTotalSales() + order.getFinalPrice());
		}
	    }

	    if (!alreadyAddedDay) {
		report.add(new DailySalesDTO(order.getDate(), order.getFinalPrice()));
	    }
	}

	return report;
    }
}
