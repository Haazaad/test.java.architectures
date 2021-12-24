package ru.haazad.java.architectures.repositories;

import org.springframework.stereotype.Repository;
import ru.haazad.java.architectures.entities.Product;

import java.util.List;

@Repository
public interface ProductRepository {

    Product findById(Long id);

    List<Product> findAll();

    void create(Product product);

    void update(Product product);

    void deleteById(Long id);

    boolean haveProduct(Long id);
}
