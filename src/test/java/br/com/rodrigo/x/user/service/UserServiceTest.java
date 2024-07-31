package br.com.rodrigo.x.user.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.rodrigo.x.user.dto.UserDTO;
import br.com.rodrigo.x.user.model.User;
import br.com.rodrigo.x.user.model.enumeration.Role;
import br.com.rodrigo.x.user.repository.UserRepository;

@SpringBootTest
public class UserServiceTest {

	@Autowired
	public UserRepository userRepository;

	@Autowired
	public UserService userService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void test_saveUser() {
		UserDTO userDTO = new UserDTO();
		userDTO.setRole(Role.ADMIN);
		userDTO.setEmail("teste@teste.com");
		userDTO.setPassword("123456");
		userDTO.setFirstName("Rodrigo");
		userDTO.setLastName("Barbosa");
		userDTO.setDateOfBirth(LocalDate.of(2003, 03, 02));
		userDTO.setDateOfCreation(LocalDateTime.now());
		User user = userService.fromUserDTO(userDTO, null);
		// configurar o comportamento esperado no mock
		User userSaved = userService.saveUser(user);
		System.out.println(userSaved);
		assertEquals("Rodrigo", userSaved.getFirstName());
	}

	@Test
	public void test_createUser() {
		// Criar um objeto UserDTO de teste
		UserDTO userDTO = new UserDTO();
		userDTO.setRole(Role.ADMIN);
		userDTO.setEmail("teste@teste.com");
		userDTO.setPassword("123456");
		userDTO.setFirstName("Rodrigo");
		userDTO.setLastName("Barbosa");
		userDTO.setDateOfBirth(LocalDate.of(2003, 3, 2));
		userDTO.setDateOfCreation(LocalDateTime.now());

		// Chamar o metodo createUser da UserService
		User userSaved = userService.createUser(userDTO);

		// Verificar se o usuario salvo tem os valores esperados
		assertNotNull(userSaved, "não salvo, null!");
		assertEquals("Rodrigo", userSaved.getFirstName());
		assertEquals("Barbosa", userSaved.getLastName());
		assertEquals("teste@teste.com", userSaved.getEmail());
		assertEquals("123456", userSaved.getPassword());
		assertEquals(Role.ADMIN, userSaved.getRole());
		assertEquals(LocalDate.of(2003, 3, 2), userSaved.getDateOfBirth());
		assertNotNull(userSaved.getDateOfCreation());
	}
	
	@Test
	public void test_findById() {
		Optional<User> user = userService.findUserById(UUID.fromString("0c35b469-c473-4c0a-910a-682fe70cf874"));
		user.ifPresent(userEx -> {
			System.out.println(userEx.getFirstName());
			System.out.println(userEx.getLastName());
		});
	}
	
	@Test
	public void test_updateUser() {
		User user = new User();
		user.setFirstName("Primeiro nome");
		user.setLastName("Segundo nome");
		User userUpdated = userService.updateUser(user, UUID.fromString("0c35b469-c473-4c0a-910a-682fe70cf874"));
		assertNotNull(userUpdated, "não salvo, null!");
	}
	
	@Test
	public void test_deleteUser() {
		try {
			userService.deleteUser(UUID.fromString("0cbfff-c473-4c0a-910a-682fe70cf874"));
		} catch (Exception e) {
			assertEquals("deleteUser - msg: Usuario nao existe", e.getMessage());
		}
	}
}
