package ru.haazad.java.architectures.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.haazad.java.architectures.dtos.OrderDto;
import ru.haazad.java.architectures.dtos.OrderItemDto;
import ru.haazad.java.architectures.dtos.ProductDto;
import ru.haazad.java.architectures.entities.Order;
import ru.haazad.java.architectures.entities.OrderItem;
import ru.haazad.java.architectures.entities.Product;
import ru.haazad.java.architectures.services.ProductService;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class Converter {
    private final ProductService productService;

    public ProductDto productToProductDto(Product product) {
        return new ProductDto(product.getId(), product.getTitle(), product.getPrice(), product.getUpdateDate());
    }

    public OrderDto orderToOrderDto(Order order) {
        return new OrderDto(order.getId(), order.getItems().stream().map(this::orderItemToDto).collect(Collectors.toList()), order.getTotalPrice());
    }

    private OrderItemDto orderItemToDto(OrderItem item) {
        Product product = productService.getProductById(item.getProductId());
        return new OrderItemDto(product.getId(), product.getTitle(), item.getQuantity(), item.getProductPrice(), item.getTotalPrice());
    }
}
