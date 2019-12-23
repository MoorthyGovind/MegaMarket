package com.store.stock.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TransactionRequestDto {

	private String userId;
	private Double transactionAmount;
	private String description;
	private Long cardNumber;
}
