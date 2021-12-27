package ru.haazad.java.architectures.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.haazad.java.architectures.dtos.OrderDto;
import ru.haazad.java.architectures.dtos.OrderItemDto;
import ru.haazad.java.architectures.entities.Order;
import ru.haazad.java.architectures.entities.OrderItem;
import ru.haazad.java.architectures.entities.Product;
import ru.haazad.java.architectures.entities.User;
import ru.haazad.java.architectures.repositories.OrderRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserService userService;
    private final ProductService productService;

    public List<Order> findAllByUserId(Long userId) {
        return orderRepository.findAllByUserId(userId);
    }

    public Order findOrderById(Long orderId) {
        return orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException(String.format("Order with id=%d not found", orderId)));
    }

    @Transactional
    public void saveOrder(OrderDto orderDto, String username) {
        User user = userService.getUserByUsername(username);
        Order order = new Order();
        List<OrderItem> items = new ArrayList<>();
        for (OrderItemDto itemDto : orderDto.getItems()) {
            Product product = productService.getProductById(itemDto.getProductId());
            items.add(OrderItem.builder()
                    .product(product.getId())
                    .quantity(itemDto.getQuantity())
                    .productPrice(itemDto.getProductPrice())
                    .totalPrice(itemDto.getTotalPrice())
                    .build());
        }
        order.setItems(items);
        order.setUser(user);
        order.setTotalPrice(orderDto.getTotalPrice());
        orderRepository.save(order);
    }

}
