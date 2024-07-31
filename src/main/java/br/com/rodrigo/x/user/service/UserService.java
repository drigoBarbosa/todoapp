package br.com.rodrigo.x.user.service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rodrigo.x.user.dto.UserDTO;
import br.com.rodrigo.x.user.model.User;
import br.com.rodrigo.x.user.repository.UserRepository;
import br.com.rodrigo.x.util.exception.BusinessRuleException;
import br.com.rodrigo.x.util.exception.UserNotFoundException;
import jakarta.validation.constraints.NotNull;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public User createUser(UserDTO userDTO) {
		return createUserImpl(userDTO);
	}

	private User createUserImpl(UserDTO userDTO) {
		User user = fromUserDTO(userDTO, null);
		return saveUser(user);
	}

	public User saveUser(User user) {
		return saveUserImpl(user);
	}

	private User saveUserImpl(User user) {
		user.setDateOfCreation(LocalDateTime.now());
		return userRepository.save(user);
	}

	public User fromUserDTO(UserDTO userDTO, User user) {
		return fromUserDTOImpl(userDTO, user);
	}

	private User fromUserDTOImpl(UserDTO userDTO, User user) {
		if (user == null) {
			user = new User();
		}
		user.setId(userDTO.getId());
		user.setFirstName(userDTO.getFirstname());
		user.setLastName(userDTO.getLastname());
		user.setEmail(userDTO.getEmail());
		user.setPassword(userDTO.getPassword());
		user.setRole(userDTO.getRole());
		user.setDateOfBirth(userDTO.getDateOfBirth());
		user.setDateOfCreation(userDTO.getDateOfCreation());
		return user;
	}

	public UserDTO fromUser(User user, UserDTO userDTO) {
		return fromUserImpl(user, userDTO);
	}

	private UserDTO fromUserImpl(User user, UserDTO userDTO) {
		if (userDTO == null) {
			userDTO = new UserDTO();
		}
		userDTO.setFirstName(user.getFirstName());
		userDTO.setLastName(user.getLastName());
		userDTO.setEmail(user.getEmail());
		userDTO.setPassword(user.getPassword());
		userDTO.setRole(user.getRole());
		userDTO.setDateOfBirth(user.getDateOfBirth());
		userDTO.setDateOfCreation(user.getDateOfCreation());
		return userDTO;
	}

	public Optional<User> findUserById(UUID uuid) {
		return userRepository.findById(uuid);
	}

	public User updateUser(User user, UUID uuid) {
		return updateUserImpl(user, uuid);
	}

	private User updateUserImpl(User user, UUID uuid) {
		if (uuid == null) {
			throw new BusinessRuleException("updateUser - msg: id com valor null");
		}
		User userUpdating = userRepository.findById(uuid)
				.orElseThrow(() -> new UserNotFoundException("updateUser - msg: Usuario nao existe"));

		if (user.getFirstName() != null) {
			userUpdating.setFirstName(user.getFirstName());
		}
		if (user.getLastName() != null) {
			userUpdating.setLastName(user.getLastName());
		}
		if (user.getDateOfBirth() != null) {
			userUpdating.setDateOfBirth(user.getDateOfBirth());
		}
		if (user.getEmail() != null) {
			userUpdating.setEmail(user.getEmail());
		}
		if (user.getPassword() != null) {
			userUpdating.setPassword(user.getPassword());
		}
		if (user.getRole() != null) {
			userUpdating.setRole(user.getRole());
		}

		return userRepository.save(userUpdating);
	}

	public void deleteUser(@NotNull UUID uuid) {
		if (!findUserById(uuid).isPresent()) {
			throw new UserNotFoundException("deleteUser - msg: Usuario nao existe");
		}
		userRepository.deleteById(uuid);
	}

}
