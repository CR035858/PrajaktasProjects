package com.Orders.SpringRestfulAPI.Model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
@Builder

@Entity
@Table(name = "orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
    
	@NotEmpty(message="customer name cannot be empty")
	private String customerName;

	@Min(value=2000, message="order price cannot be less than 2000")
	@Max(value=50000, message="order price cannot be more than 50000")
	private double price;
	
	@Column(name="email_adress", nullable = false )
	@Email(message="email should be in correct format")
	private String email;
	
	@PastOrPresent(message = "order data cannot be in the future")
	private LocalDate orderDate;

@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
@JsonManagedReference
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