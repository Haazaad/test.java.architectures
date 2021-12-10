package ru.haazad.java.architectures.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.haazad.java.architectures.entities.Order;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Override
    Optional<Order> findById(Long orderId);

    List<Order> findAllByUserId(Long userId);

}
