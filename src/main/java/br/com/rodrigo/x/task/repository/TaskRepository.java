package br.com.rodrigo.x.task.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.rodrigo.x.task.model.Task;

public interface TaskRepository extends JpaRepository<Task, UUID>{
}
