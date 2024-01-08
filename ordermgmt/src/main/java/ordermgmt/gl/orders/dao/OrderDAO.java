package ordermgmt.gl.orders.dao;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import ordermgmt.gl.orders.model.Order;


@Repository
public class OrderDAO {
	
	
	SessionFactory sessionFactory;
	Session session;
	
	public OrderDAO(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
		this.session = sessionFactory.openSession();
	}
	public Order saveOrder(Order order) {
		Transaction tx = session.beginTransaction();
		session.save(order);
		tx.commit();
		return order;
	}
	
	public List <Order> fetchAll(){
		Transaction tx = session.beginTransaction();
		List <Order> orders  = session.createQuery("from Order").list();
		tx.commit();
		return orders;
	}
	
	public Order findOrderById(int id) {
		Order order = new Order();
		Transaction tx = session.beginTransaction();
			order = session.get(Order.class, id);
		tx.commit();
		return order;
	}
	
	public Order updateOrder(int id, Order order) {
		
		Order orderU = findOrderById(id);
		orderU.setOrderPrice(order.getOrderPrice());
		orderU.setOrderDate(order.getOrderDate());
		orderU.setCustomerName(order.getCustomerName());
		Transaction tx = session.beginTransaction();
			session.saveOrUpdate(orderU);
		tx.commit();
		return orderU;
	}
	
	public void deleteOrderById(int id) {
		Transaction tx = session.beginTransaction();
		Order order = session.get(Order.class, id);
		session.delete(order);
		tx.commit();
	}

}
