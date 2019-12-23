package com.store.stock.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidateOtpDto {

	private Long cardNumber;
	private Integer otpValue;
}
