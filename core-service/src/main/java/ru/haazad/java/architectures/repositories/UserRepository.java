package ru.haazad.java.architectures.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.haazad.java.architectures.entities.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Override
    Optional<User> findById(Long id);

    Optional<User> findByUsername(String username);
}
