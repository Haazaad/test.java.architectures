package ru.haazad.java.architectures.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.haazad.java.architectures.entities.Product;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Override
    Optional<Product> findById(Long id);

    @Override
    List<Product> findAll();

}
