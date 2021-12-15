package ru.haazad.java.architectures.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderDto {

    private Long id;

    private List<OrderItemDto> items;

    private BigDecimal totalPrice;
}
