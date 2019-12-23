package com.store.stock.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.store.stock.constant.AppConstant;
import com.store.stock.dto.BuyProductRequestDto;
import com.store.stock.dto.ResponseDto;
import com.store.stock.dto.ValidateOtpDto;
import com.store.stock.exception.OrderNotFoundException;
import com.store.stock.exception.ProductNotFoundException;
import com.store.stock.exception.UserNotFoundException;
import com.store.stock.service.OrderService;

/**
 * OrderController - In E-commerce application user order(buy) a products and
 * view the user order details of the Rest Api's implement in this Order
 * Controller.
 * 
 * @author Govindasamy.C
 * @version V1.1
 * @since 23-12-2019
 *
 */
@RequestMapping("/users/{userId}")
@RestController
@CrossOrigin
public class OrderController {
	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

	@Autowired
	OrderService orderService;

	/**
	 * Buy a product based on the userId.
	 * 
	 * @param userId
	 * @param buyProductRequestDto
	 * @return
	 * @throws ProductNotFoundException if product is not found we can throw the
	 *                                  productnotfoundexception.
	 * @throws UserNotFoundException    if user is not found we can throw the
	 *                                  usernotfoundexception.
	 */
	@PostMapping("/orders")
	public ResponseEntity<ResponseDto> buyProduct(@PathVariable String userId,
			@Valid @RequestBody BuyProductRequestDto buyProductRequestDto)
			throws ProductNotFoundException, UserNotFoundException {
		logger.info("validate otp value...");
		ResponseDto responseDto = new ResponseDto();
		// Buy the product service call.
		orderService.buyProduct(userId, buyProductRequestDto);
		responseDto.setMessage(AppConstant.BUY_SUCCESS_OTP_SEND);
		responseDto.setStatusCode(HttpStatus.CREATED.value());
		return new ResponseEntity<>(responseDto, HttpStatus.OK);
	}

	/**
	 * validate the otp value based on the order id value.
	 * 
	 * @param userId
	 * @param orderId
	 * @param validateOtpDto
	 * @return 
	 * @throws ProductNotFoundException
	 * @throws UserNotFoundException
	 * @throws OrderNotFoundException
	 */
	@PutMapping("/orders/{orderId}")
	public ResponseEntity<ResponseDto> validateOtp(@PathVariable String userId, @PathVariable Integer orderId,
			@Valid @RequestBody ValidateOtpDto validateOtpDto)
			throws ProductNotFoundException, UserNotFoundException, OrderNotFoundException {
		logger.info("buy a product based on the userId");
		ResponseDto responseDto = new ResponseDto();
		// Buy the product service call.
		orderService.validateOtp(userId, orderId, validateOtpDto);
		responseDto.setMessage(AppConstant.ORDER_SUCCESS_MESSAGE);
		responseDto.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<>(responseDto, HttpStatus.OK);
	}
}
