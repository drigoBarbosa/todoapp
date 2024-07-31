package br.com.rodrigo.x.user.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import br.com.rodrigo.x.user.model.enumeration.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_user")
public class User {
	
	@Id
	@Column(name = "id_user", nullable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "role", nullable = false)
	private Role role;
	
	@Column(name = "firstname", nullable = false)
	private String firstName;
	
	@Column(name = "lastname")
	private String lastName;
	
	@Past
	@Column(name = "date_of_birth", nullable = false)
	private LocalDate dateOfBirth;
	
	@Column(name = "date_of_creation")
	private LocalDateTime dateOfCreation;
	
	@Column(name = "email", nullable = false)
	private String email;
	
	@Column(name = "password", nullable = false)
	private String password;
}
