package com.store.stock.service;

import com.store.stock.dto.BuyProductRequestDto;
import com.store.stock.dto.ValidateOtpDto;
import com.store.stock.exception.OrderNotFoundException;
import com.store.stock.exception.ProductNotFoundException;
import com.store.stock.exception.UserNotFoundException;

public interface OrderService {

	public void buyProduct(String userId, BuyProductRequestDto buyProductRequestDto)
			throws ProductNotFoundException, UserNotFoundException;

	public void validateOtp(String userId, Integer orderId, ValidateOtpDto validateOtpDto)
			throws UserNotFoundException, OrderNotFoundException;
}
