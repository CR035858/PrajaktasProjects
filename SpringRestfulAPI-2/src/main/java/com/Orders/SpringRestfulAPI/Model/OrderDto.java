package com.Orders.SpringRestfulAPI.Model;

import java.time.LocalDate;

public interface OrderDto {
	
	String getCustomerName();
	
	double getPrice();
	
	String getEmail();
	
	LocalDate getOrderDate();

}
