package com.store.stock.service;

import java.time.LocalDate;
import java.util.Optional;

import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.store.stock.constant.AppConstant;
import com.store.stock.dto.BuyProductRequestDto;
import com.store.stock.dto.TransactionRequestDto;
import com.store.stock.dto.TransactionResponseDto;
import com.store.stock.dto.ValidateOtpDto;
import com.store.stock.entity.Order;
import com.store.stock.entity.Product;
import com.store.stock.entity.User;
import com.store.stock.exception.OrderNotFoundException;
import com.store.stock.exception.ProductNotFoundException;
import com.store.stock.exception.UserNotFoundException;
import com.store.stock.repository.OrderRepository;
import com.store.stock.repository.ProductRepository;
import com.store.stock.repository.UserRepository;

/**
 * OrderController - In E-commerce application user order(buy) a products and
 * view the user order details of the Rest Api's method implement in this Order
 * Service.
 * 
 * @author Govindasamy.C
 * @version V1.1
 * @since 23-12-2019
 *
 */
@Service
public class OrderServiceImpl implements OrderService {
	private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

	@Autowired
	ProductRepository productRepository;

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	SendEmailService sendEmailService;

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
	@Override
	public void buyProduct(String userId, BuyProductRequestDto buyProductRequestDto)
			throws ProductNotFoundException, UserNotFoundException {
		logger.info("buy a product based on user input...");
		// Check product detail is present or not.
		Optional<Product> product = productRepository.findById(buyProductRequestDto.getProductId());
		if (!product.isPresent()) {
			throw new ProductNotFoundException(AppConstant.PRODUCT_NOT_FOUND);
		}

		// Check product detail is present or not.
		Optional<User> user = userRepository.findByEmailId(userId);
		if (!user.isPresent()) {
			throw new UserNotFoundException(AppConstant.USER_NOT_FOUND);
		}
		logger.debug("buy a product based on user input...");

		// Save the order details.
		Order order = new Order();
		order.setAmount(product.get().getPriceValue());
		order.setOrderDate(LocalDate.now());
		order.setProduct(product.get());
		order.setUser(user.get());

		// Genetate and save the otp value.
		Integer otpValue = generateOtpValue();
		order.setOtpValue(otpValue);

		orderRepository.save(order);

		// Send Email Service.
		// sendEmailService.sendEmail("moorthy127@gmail.com", null, null);
	}

	@Override
	public void validateOtp(String userId, Integer orderId, ValidateOtpDto validateOtpDto)
			throws UserNotFoundException, OrderNotFoundException {
		// Check product detail is present or not.
		Optional<User> user = userRepository.findByEmailId(userId);
		if (!user.isPresent()) {
			throw new UserNotFoundException(AppConstant.USER_NOT_FOUND);
		}

		// Check Order detail is present or not.
		Optional<Order> order = orderRepository.findById(orderId);
		if (!order.isPresent()) {
			throw new OrderNotFoundException(AppConstant.ORDER_NOT_FOUND);
		}

		// Check Order detail is present or not.
		Optional<Order> orderResult = orderRepository.findByOtpValue(validateOtpDto.getOtpValue());
		if (!orderResult.isPresent()) {
			throw new OrderNotFoundException(AppConstant.OTP_WRONG_VALUE);
		}

		// Call the Rest Template for Update the transaction details.
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8080/creditcard/transactions";
		TransactionRequestDto transactionRequestDto = new TransactionRequestDto();
		transactionRequestDto.setCardNumber(validateOtpDto.getCardNumber());
		transactionRequestDto.setTransactionAmount(order.get().getAmount());
		transactionRequestDto.setDescription(order.get().getProduct().getDescription());
		transactionRequestDto.setUserId(userId);
		ResponseEntity<TransactionResponseDto> response = restTemplate.postForEntity(url, transactionRequestDto,
				TransactionResponseDto.class);
		System.out.println("response body::: " + response.getBody());

	}

	/**
	 * get the otp value based on the numeric values.
	 * 
	 * @return return the long value of the generated otp value.
	 */
	private Integer generateOtpValue() {
		String number = RandomStringUtils.random(6, false, true);
		return Integer.valueOf(number);
	}

}
