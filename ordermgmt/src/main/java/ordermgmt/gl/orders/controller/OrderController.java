package ordermgmt.gl.orders.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ordermgmt.gl.orders.model.Order;



@Controller
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	private ordermgmt.gl.orders.service.OrderService orderService;
	
	@RequestMapping("/sayHello")
	public String sayHello(Model model)
	{
		System.out.println("yes Controller is this");
		String str = "Welcome to Order Mgmt System";
		model.addAttribute("message", str);
		return "welcome";
	}
	
	@RequestMapping("/save")
	public String saveOrder(@RequestParam("id") int oId,
								@RequestParam("customerName") String customerName,
								@RequestParam("orderPrice") double orderPrice,
								@RequestParam("orderDate") String orderDate,
								Model model)
	{
		Order order = new Order();
		LocalDate d1 = LocalDate.parse(orderDate); 
		if(oId != 0)
		{
			order = orderService.findOrderById(oId);
			order.setCustomerName(customerName);
			order.setOrderPrice(orderPrice);
			   
			order.setOrderDate(d1);
		}
		else
		{
			order = new Order(customerName,orderPrice,d1);
		}
		orderService.saveOrder(order);
		return "redirect:/orders/list";
	}
	
	//showFormForUpdate
	@RequestMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("orderId") int orderId,Model model)
	{
		Order order = orderService.findOrderById(orderId);
		model.addAttribute("order", order);
		return "order-form";
	}
	
	
	@RequestMapping("/showFormForAdd")
	public String showAddOrderForm(Model model)
	{
		Order order = new Order();
		model.addAttribute("order", order); //
		return "order-form";
	}
	
	

	@RequestMapping("/update")
	public Order updateOrder(int id, Order order) {
		return this.orderService.updateOrder(id, order);
	}

	@RequestMapping("/list")
	public String fetchALlOrders(Model model) {
		List <Order> orders = orderService.fetchAllOrders();
		model.addAttribute("orders", orders);
		return "order-list";
	}

	@RequestMapping("/orderById")
	public Order findOrderById(int id) {
		return this.orderService.findOrderById(id);
	}

	@RequestMapping("/delete")
	public String deleteOrderById(@RequestParam("orderId") int orderId) {
		this.orderService.deleteOrderById(orderId);
		return "redirect:/orders/list";
	}
}
