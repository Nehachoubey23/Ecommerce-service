package ecommerce.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import ecommerce.dto.OrderItemDTO;
import ecommerce.dto.OrderResponse;
import ecommerce.model.CartItem;
import ecommerce.model.Order;
import ecommerce.model.OrderItem;
import ecommerce.model.OrderStatus;
import ecommerce.model.User;
import ecommerce.repo.OrderRepository;
import ecommerce.repo.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

	private final OrderRepository orderRepository;
	private final CartService cartService;
	private final UserRepository userRepository;

	@Override
	public Optional<OrderResponse> createOrder(String userId) {
		// TODO Auto-generated method stub
		List<CartItem> cartItems = cartService.fetchAllCarts(userId);
		if (cartItems.isEmpty()) {
			return Optional.empty();
		}
		Optional<User> useroptional = userRepository.findById(Long.valueOf(userId));
		if (useroptional.isEmpty()) {

			return Optional.empty();
		}
		User user = useroptional.get();
		BigDecimal totalPrice = cartItems.stream().
				map(CartItem::getPrice).
				reduce(BigDecimal.ZERO, BigDecimal::add);
		
		Order order = new Order();
		order.setUser(user);
		order.setStatus(OrderStatus.COFIRMED);
		order.setTotalAmount(totalPrice);
		List <OrderItem> orderitem = cartItems.stream().map(item -> new OrderItem(
				null,
				item.getProduct(),
				item.getQuantity(),
				item.getPrice(),
				order
				)).toList();
		order.setItems(orderitem);
		Order Savedorder = orderRepository.save(order);
		cartService.clearCart(userId);
		return Optional.of(maptoOrderResponse(Savedorder));
	}
	private OrderResponse maptoOrderResponse(Order savedorder) {

	    return new OrderResponse(
	            savedorder.getId(),
	            savedorder.getTotalAmount(),   // 2nd parameter
	            savedorder.getStatus(),        // 3rd parameter
	            savedorder.getItems().stream()
	                    .map(item -> new OrderItemDTO(
	                            item.getId(),
	                            item.getProduct().getId(),
	                            item.getQuantity(),
	                            item.getPrice(),
	                            item.getPrice().multiply(
	                                    BigDecimal.valueOf(item.getQuantity()))
	                    ))
	                    .toList(),
	            savedorder.getCreatedAt()
	    );
	}
	

}
