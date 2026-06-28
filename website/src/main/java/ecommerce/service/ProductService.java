package ecommerce.service;

import java.util.List;
import java.util.Optional;

import ecommerce.dto.ProductResponse;
import ecommerce.dto.ProductResquest;

public interface ProductService {

	ProductResponse create(ProductResquest productrequest);

	Optional<ProductResponse> updateProduct(Long id, ProductResquest productrequest);

	List<ProductResponse> fetchAllProducts();

	boolean DeletedProducts(Long id);

	List<ProductResponse> searchProducts(String keyword);

}
