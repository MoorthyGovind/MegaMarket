package com.store.stock.dto;

import javax.validation.constraints.NotNull;

import com.store.stock.constant.AppConstant;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BuyProductRequestDto {

	@NotNull(message = AppConstant.CREDIT_NUMBER_REQUIRED)
	private Integer cardNumber;
	@NotNull(message = AppConstant.HOLDER_NAME_REQUIRED)
	private String holderName;
	@NotNull(message = AppConstant.CVV_REQUIRED)
	private Integer cvv;
	private String validFrom;
	private String validTo;
	private Integer productId;

}
