package ru.haazad.java.architectures.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.haazad.java.architectures.dtos.OrderDto;
import ru.haazad.java.architectures.services.OrderService;
import ru.haazad.java.architectures.utils.Converter;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {
    private static final String USER = "user";

    private final OrderService orderService;
    private final Converter converter;

    @GetMapping("/{orderId}")
    public OrderDto findOrderById(@PathVariable Long orderId) {
        return converter.orderToOrderDto(orderService.findOrderById(orderId));
    }

    @GetMapping
    public List<OrderDto> findAllByUserId(@RequestParam Long userId) {
        return orderService.findAllByUserId(userId).stream().map(converter::orderToOrderDto).collect(Collectors.toList());
    }

    @PostMapping
    public void saveOrder(@RequestBody OrderDto orderDto) {
        orderService.saveOrder(orderDto, USER);
    }

}
