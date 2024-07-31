package br.com.rodrigo.x.user.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import br.com.rodrigo.x.user.model.enumeration.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

	private UUID id;
	
	private Role role;

	@NotBlank(message = "Campo obrigatório!")
	private String firstName;

	@NotBlank(message = "Campo obrigatório!")
	private String lastName;

	@Past
	@NotNull(message = "Campo obrigatório!")
	private LocalDate dateOfBirth;
	
	private LocalDateTime dateOfCreation;

	@NotBlank(message = "Campo obrigatório!")
	@Size(max = 100, message = "Máximo de caracteres ultrapassado, máximo permitido de 100 caracteres.")
	private String email;

	@NotBlank(message = "Campo obrigatório!")
	@Size(max = 16, message = "Máximo de caracteres ultrapassado, máximo permitido de 16 caracteres.")
	private String password;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getFirstname() {
		return firstName;
	}

	public void setFirstName(String firstname) {
		this.firstName = firstname;
	}

	public String getLastname() {
		return lastName;
	}

	public void setLastName(String lastname) {
		this.lastName = lastname;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public LocalDateTime getDateOfCreation() {
		return dateOfCreation;
	}

	public void setDateOfCreation(LocalDateTime dateOfCreation) {
		this.dateOfCreation = dateOfCreation;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
