package com.store.stock.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TransactionResponseDto {

	private String message;
	private Integer statusCode;
	private String transactionNumber;
}
