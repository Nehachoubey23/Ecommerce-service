package ecommerce.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ecommerce.model.CartItem;
import ecommerce.model.Product;
import ecommerce.model.User;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem,Long>{

	CartItem findByUserAndProduct(User user, Product product);

	void deleteByUserAndProduct(User user, Product product);
	List <CartItem> findByUser(User user );

}
