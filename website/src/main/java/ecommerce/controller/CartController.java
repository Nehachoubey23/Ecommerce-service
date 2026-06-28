package ecommerce.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ecommerce.dto.CartItemRequest;
import ecommerce.service.CartService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/cart")
public class CartController {

	private final CartService cartService;

	@PostMapping
	public ResponseEntity<String> addToCart(@RequestHeader("X-USER-ID") String userId,
			@RequestBody CartItemRequest request) {
		if (!cartService.addToCart(userId, request)) {
			return ResponseEntity.badRequest().body("Product Out of Stock or User not found");
		}
		return ResponseEntity.status(HttpStatus.CREATED).build();

	}

}