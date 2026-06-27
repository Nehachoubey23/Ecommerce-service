package ecommerce.service;

import java.util.Optional;

import ecommerce.dto.ProductResponse;
import ecommerce.dto.ProductResquest;

public interface ProductService {

	ProductResponse create(ProductResquest productrequest);

	Optional<ProductResponse> updateProduct(Long id, ProductResquest productrequest);

}
