package com.gl.ordersrest.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;



import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@ToString
@EqualsAndHashCode

@Entity
@Table(name = "orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String customerName;

	private double price;

	private LocalDate orderDate;

@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<LineItem> lineItems;

	// Scafolding Code
	public void addLineItem(LineItem lineItem) {
		if (this.lineItems == null) {
			this.lineItems = new HashSet<>();
		}
		this.lineItems.add(lineItem);
		lineItem.setOrder(this);
	}
}
