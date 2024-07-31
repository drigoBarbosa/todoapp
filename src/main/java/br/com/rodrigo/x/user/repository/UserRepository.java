package br.com.rodrigo.x.user.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.rodrigo.x.user.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID>{
}
