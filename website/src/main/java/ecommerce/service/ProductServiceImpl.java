package ecommerce.service;

import org.springframework.stereotype.Service;

import ecommerce.dto.ProductResponse;
import ecommerce.dto.ProductResquest;
import ecommerce.repo.ProductRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

	private final ProductRepository productRepository;

	@Override
	public ProductResponse create(ProductResquest productrequest) {
		// TODO Auto-generated method stub
		return null;
	}
}
