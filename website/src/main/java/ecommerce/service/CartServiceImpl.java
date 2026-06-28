package ecommerce.service;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.stereotype.Service;

import ecommerce.dto.CartItemRequest;
import ecommerce.model.CartItem;
import ecommerce.model.Product;
import ecommerce.model.User;
import ecommerce.repo.CartItemRepository;
import ecommerce.repo.ProductRepository;
import ecommerce.repo.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

	private final CartItemRepository cartItemRepository;

	private final ProductRepository productRepository;

	private final UserRepository userRepository;

	@Override
	public boolean addToCart(String userId, CartItemRequest request) {
		// TODO Auto-generated method stub
		Optional<Product> productopt = productRepository.findById(request.getProductId());
		if (productopt.isEmpty())
			return false;

		Product product = productopt.get();
		if (product.getStockQuantity() < request.getQuantity())
			return false;

		Optional<User> useropt = userRepository.findById(Long.valueOf(userId));
		if (useropt.isEmpty())
			return false;

		User user = useropt.get();

		CartItem existingCartItem = cartItemRepository.findByUserandProduct(user, product);
		if(existingCartItem != null) {
			existingCartItem.setQuantity(existingCartItem.getQuantity() + request.getQuantity() );
			existingCartItem.setPrice(product.getPrice().multiply(BigDecimal.valueOf(existingCartItem.getQuantity())));
			cartItemRepository.save(existingCartItem);
		}
		else {
			CartItem cartItem = new CartItem();
			cartItem.setUser(user);
			cartItem.setProduct(product);
			cartItem.setQuantity(request.getQuantity());
			cartItem.setPrice(product.getPrice().multiply(BigDecimal.valueOf(request.getQuantity())));
			cartItemRepository.save(cartItem);
			
		}
		return true;

	}
}
