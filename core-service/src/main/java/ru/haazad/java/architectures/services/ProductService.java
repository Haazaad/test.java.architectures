package ru.haazad.java.architectures.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.haazad.java.architectures.dtos.ProductDto;
import ru.haazad.java.architectures.entities.Product;
import ru.haazad.java.architectures.repositories.ProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long productId) {
        return productRepository.findById(productId);
    }

    public void saveProduct(ProductDto productDto) {
        if (productRepository.haveProduct(productDto.getId())) {
            productRepository.update(setProduct(productDto));
            return;
        }
        productRepository.create(setProduct(productDto));
    }

    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }

    public boolean haveProduct(Long id) {
        return productRepository.haveProduct(id);
    }

    private Product setProduct(ProductDto productDto) {
        return Product.builder()
                .id(productDto.getId())
                .title(productDto.getTitle())
                .price(productDto.getPrice())
                .build();
    }
}
