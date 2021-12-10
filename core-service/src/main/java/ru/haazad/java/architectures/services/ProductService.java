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
        return productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public void saveProduct(ProductDto productDto) {
        productRepository.save(setProduct(productDto));
    }

    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }

    private Product setProduct(ProductDto productDto) {
        Product product = new Product();
        product.setId(product.getId());
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        return product;
    }
}
