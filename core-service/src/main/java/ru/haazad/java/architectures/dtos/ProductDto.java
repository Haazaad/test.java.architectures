package ru.haazad.java.architectures.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductDto {

    private Long id;

    @Nullable
    private String title;

    @Nullable
    private BigDecimal price;

    @Nullable
    private LocalDateTime updateDate;

}
