package ru.haazad.java.architectures.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderItemDto {

    private Long productId;

    private String productTitle;

    private int quantity;

    private BigDecimal productPrice;

    private BigDecimal totalPrice;

    public void changeQuantity(int delta) {
        quantity += delta;
        totalPrice = productPrice.multiply(new BigDecimal(quantity));
    }

}
