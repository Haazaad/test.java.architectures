package ru.haazad.java.architectures.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductDto {

    private Long id;

    private String title;

    private BigDecimal price;

    private LocalDateTime updateDate;
}
