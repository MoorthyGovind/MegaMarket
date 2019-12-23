package com.store.stock.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Product {

	@Id
	private Integer productId;
	private String productName;
	private Integer quantityAvailable;
	private Double priceValue;
	private String description;

}
