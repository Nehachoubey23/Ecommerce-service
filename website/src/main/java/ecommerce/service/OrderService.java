package ecommerce.service;

import java.util.Optional;

import ecommerce.dto.OrderResponse;

public interface OrderService {

	Optional<OrderResponse> createOrder(String userId);

}
