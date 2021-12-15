package ru.haazad.java.architectures.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.haazad.java.architectures.dtos.OrderDto;
import ru.haazad.java.architectures.entities.Order;
import ru.haazad.java.architectures.repositories.OrderRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserService userService;

    public List<Order> findAllByUserId(Long userId) {
        return orderRepository.findAllByUserId(userId);
    }

    public Order findOrderById(Long orderId) {
        return orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException(String.format("Order with id=%d not found", orderId)));
    }

//    @Transactional
//    public void saveOrder(OrderDto orderDto, String username) {}

}
