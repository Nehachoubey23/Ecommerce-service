package ecommerce.service;

import java.util.List;

import ecommerce.dto.CartItemRequest;
import ecommerce.model.CartItem;

public interface CartService {

	public boolean addToCart(String userId, CartItemRequest request);

	public boolean deleteItemFromCart(String userId, Long productId);

	List<CartItem> fetchAllCarts(String userId);

}
