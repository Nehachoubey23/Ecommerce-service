package ecommerce.service;

import ecommerce.dto.ProductResponse;
import ecommerce.dto.ProductResquest;

public interface ProductService {

	ProductResponse create(ProductResquest productrequest);

}
