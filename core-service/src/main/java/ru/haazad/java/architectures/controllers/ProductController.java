package ru.haazad.java.architectures.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.haazad.java.architectures.dtos.ProductDto;
import ru.haazad.java.architectures.services.ProductService;
import ru.haazad.java.architectures.utils.Converter;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;
    private final Converter converter;

    @GetMapping
    public List<ProductDto> getAllProducts() {
        log.debug("Products " + productService.getAllProducts().stream().map(converter::productToProductDto).collect(Collectors.toList()));
        return productService.getAllProducts().stream().map(converter::productToProductDto).collect(Collectors.toList());
    }

    @GetMapping("/{productId}")
    public ProductDto getProduct(@PathVariable Long productId) {
        return converter.productToProductDto(productService.getProductById(productId));
    }

    @PostMapping
    public void createProduct(@RequestBody ProductDto productDto) {
        productService.saveProduct(productDto);
    }

    @PostMapping("/{productId}")
    public ResponseEntity<?> modifyProduct(@PathVariable Long productId, @RequestBody ProductDto productDto) {
        if (productService.getProductById(productId) != null) {
            productService.saveProduct(productDto);
            return ResponseEntity.ok("Product update successful");
        }
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{productId}")
    public void deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
    }

}
