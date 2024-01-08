package ordermgmt.gl.orders.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ordermgmt.gl.orders.model.Order;


@Service
public interface OrderService {
	
	Order saveOrder(Order order);
	
	Order updateOrder(int id, Order order);
	
	List <Order> fetchAllOrders();
	
	Order findOrderById(int id);
	
	void deleteOrderById(int id);

}
