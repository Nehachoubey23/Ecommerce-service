package ecommerce.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ecommerce.dto.ProductResponse;
import ecommerce.dto.ProductResquest;
import ecommerce.service.ProductService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/products")
public class ProductController {

	private final ProductService productService;
	
	@PostMapping
	public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductResquest productrequest){
		
		 return ResponseEntity.status(HttpStatus.CREATED)
		            .body(productService.create(productrequest));
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ProductResponse> updateProduct(
	        @PathVariable Long id,
	        @RequestBody ProductResquest productrequest) {

	    return productService.updateProduct(id, productrequest)
	            .map(ResponseEntity::ok)
	            .orElseGet(() -> ResponseEntity.notFound().build());
	}
}