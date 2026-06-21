package ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import ecommerce.model.User;
import ecommerce.repo.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	//private List<User> userList = new ArrayList<>();
	private final UserRepository userRepository;

	@Override
	public List<User> fetchAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public void create(User user) {
		
		 User savedUser = userRepository.save(user);
		   System.out.println(savedUser);
		    System.out.println(savedUser.getAddress());
	}

	@Override
	public Optional<User> fetchUserById(Long id) {
		// TODO Auto-generated method stub
		userRepository.findById(id);
		return userRepository.findById(id);
	
		/*
		 * return userList.stream().filter(user ->
		 * user.getId().equals(id)).findFirst().map(ResponseEntity::ok)
		 * .orElse(ResponseEntity.notFound().build());
		 */
	}

	@Override
	public boolean editUserById(Long id, User updatedUser) {
		// TODO Auto-generated method stub
		return userRepository.findById(id) .map(existingUser -> {
			existingUser.setFirstName(updatedUser.getFirstName());
			existingUser.setLastName(updatedUser.getLastName());
			userRepository.save(existingUser);
			return true;

		}).orElse(false);
	}
}