package ecommerce.service;

import ecommerce.dto.CartItemRequest;

public interface CartService {

	public boolean addToCart(String userId, CartItemRequest request);

}
