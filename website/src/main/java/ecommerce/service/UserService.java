package ecommerce.service;


import java.util.List;
import java.util.Optional;

import ecommerce.dto.UserRequest;
import ecommerce.dto.UserResponse;
import ecommerce.model.User;

public interface UserService {

	List<UserResponse> fetchAllUsers();

	void create(UserRequest userrequest);

	boolean editUserById(Long id, UserRequest updatedUser);

	Optional<UserResponse> fetchUserById(Long id);

}
