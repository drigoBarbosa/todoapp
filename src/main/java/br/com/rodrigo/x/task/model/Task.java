package br.com.rodrigo.x.task.model;

import java.time.LocalDateTime;
import java.util.UUID;

import br.com.rodrigo.x.user.model.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_task")
public class Task {

	@Id
	@Column(name = "id_task", updatable = false, unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	
	@ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
	private User user;
	
	@Column(name = "title", nullable = false)
	private String title;
	
	@Column(name = "description", nullable = true)
	private String description;
	
	@Column(name = "creation_date", nullable = false)
	private LocalDateTime creationDate;
	
	@Column(name = "due_date", nullable = true)
	private LocalDateTime dueDate;
	
	@Column(name = "with_expiration", nullable = true)
	private boolean withExpiration;
}
