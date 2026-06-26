package ecommerce.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ecommerce.model.User;
import ecommerce.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/api/users")
	public ResponseEntity<List<User>> getAllUsers() {
		return new ResponseEntity<>(userService.fetchAllUsers(), HttpStatus.OK);
	}

	@PostMapping("/api/users/create")
	public ResponseEntity<String> createUsers(@RequestBody User user) {
	    System.out.println("User = " + user);
	    System.out.println("Address = " + user.getAddress());

		userService.create(user);
		return ResponseEntity.status(HttpStatus.CREATED).body("User added sucessFully.");
	}

	@GetMapping("/api/users/{id}")
	public Optional<User> checkUser(@PathVariable Long id) {
		return userService.fetchUserById(id);
	}

	@PutMapping("/api/users/{id}")
	public ResponseEntity<String> editUser(@PathVariable Long id, @RequestBody User updatedUser) {
		boolean updated = userService.editUserById(id, updatedUser);
		if (updated)
			return ResponseEntity.status(HttpStatus.OK).body("User updated sucessFully.");
		return ResponseEntity.notFound().build();
	}
}