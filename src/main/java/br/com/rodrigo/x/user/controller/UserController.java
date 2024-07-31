package br.com.rodrigo.x.user.controller;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.rodrigo.x.user.dto.UserDTO;
import br.com.rodrigo.x.user.model.User;
import br.com.rodrigo.x.user.service.UserService;
import br.com.rodrigo.x.util.exception.UserNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping("todoapp/user")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/create")
	public User createUser(@Valid @RequestBody UserDTO userDTO) {
		return userService.createUser(userDTO);
	}

	@GetMapping("/find/{uuid}")
	public Optional<User> getUserById(@NotNull @PathVariable UUID uuid) {
		return userService.findUserById(uuid);
	}

	@PutMapping("/update/{uuid}")
	public User updateUser(@Valid @RequestBody User user, @NotNull @PathVariable UUID uuid) {
		return userService.updateUser(user, uuid);
	}

	@DeleteMapping("/delete/{uuid}")
	public Object deleteUser(@NotNull @PathVariable UUID uuid) {
		try {
			userService.deleteUser(uuid);
			return ResponseEntity.noContent().build();
		} catch (UserNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
}
