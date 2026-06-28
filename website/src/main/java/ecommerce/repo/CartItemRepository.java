package ecommerce.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ecommerce.model.CartItem;
import ecommerce.model.Product;
import ecommerce.model.User;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem,Long>{

	CartItem findByUserandProduct(User user, Product product);

}
