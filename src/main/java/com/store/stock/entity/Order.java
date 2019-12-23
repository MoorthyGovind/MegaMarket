package com.store.stock.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name ="user_order")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer orderId;
	private Double amount;
	private LocalDate orderDate;
	private Integer otpValue;
	@ManyToOne
	@JoinColumn(name = "productId")
	private Product product;
	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;

}
