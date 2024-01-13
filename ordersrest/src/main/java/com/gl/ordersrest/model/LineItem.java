package com.gl.ordersrest.model;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = "order")
@EqualsAndHashCode(exclude = "order")

@Entity
@Table(name = "line_items")
public class LineItem {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String customerName;
	
	private double price;
	
	private int qty;
		
@ManyToOne
@JoinColumn(name="order_id", nullable=false)
	private Order order;
 
}

