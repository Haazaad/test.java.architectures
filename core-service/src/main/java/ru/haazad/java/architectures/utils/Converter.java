package ru.haazad.java.architectures.utils;

import org.springframework.stereotype.Component;
import ru.haazad.java.architectures.dtos.ProductDto;
import ru.haazad.java.architectures.entities.Product;

@Component
public class Converter {

    public ProductDto productToProductDto(Product product) {
        return new ProductDto(product.getId(), product.getTitle(), product.getPrice(), product.getUpdateDate());
    }
}
