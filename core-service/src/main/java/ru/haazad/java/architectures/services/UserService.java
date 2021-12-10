package ru.haazad.java.architectures.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.haazad.java.architectures.entities.User;
import ru.haazad.java.architectures.repositories.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException(String.format("User %s not found", username)));
    }
}
