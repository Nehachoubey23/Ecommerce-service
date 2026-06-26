package ecommerce.service;


import java.util.List;
import java.util.Optional;

import ecommerce.model.User;

public interface UserService {

	List<User> fetchAllUsers();

	void create(User user);

	boolean editUserById(Long id, User updatedUser);

	Optional<User> fetchUserById(Long id);

}
