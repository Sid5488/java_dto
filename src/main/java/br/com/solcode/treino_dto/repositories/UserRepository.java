package br.com.solcode.treino_dto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.solcode.treino_dto.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
}