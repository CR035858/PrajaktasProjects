package ordermgmt.gl.orders.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ordermgmt.gl.orders.dao.OrderDAO;
import ordermgmt.gl.orders.model.Order;




@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderDAO orderDAO;

	public Order saveOrder(Order order) {
		return this.orderDAO.saveOrder(order);
	}

	public Order updateOrder(int id, Order order) {
		/*
		 * Optional<Order> optionalOrder = this.orderDAO.findOrderById(id);
		 * if(optionalOrder.isPresent()) { Order existingOrder = optionalOrder.get();
		 * existingOrder.setCustomerName(order.getCustomerName());
		 * existingOrder.setOrderDate(order.getOrderDate());
		 * existingOrder.setOrderPrice(order.getOrderPrice()); return
		 * this.orderDAO.saveOrder(existingOrder); } throw new
		 * IllegalArgumentException("invalid order id passed");
		 */
		Order existingOrder = this.findOrderById(id);
		existingOrder.setCustomerName(order.getCustomerName());
		existingOrder.setOrderDate(order.getOrderDate());
		existingOrder.setOrderPrice(order.getOrderPrice());
		return this.orderDAO.saveOrder(existingOrder);
			
	}

	public List <Order> fetchAllOrders() {
		return this.orderDAO.fetchAll();
	}

	public Order findOrderById(int id) {
	/*	return ((Order) this.orderDAO.findOrderById(id))
				.orElseThrow(() -> new IllegalArgumentException("invalid order id passed"));*/
		return this.orderDAO.findOrderById(id);
	}

	public void deleteOrderById(int id) {
		this.orderDAO.deleteOrderById(id);

	}

}
