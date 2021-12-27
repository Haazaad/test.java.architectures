package ru.haazad.java.architectures.repositories.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.haazad.java.architectures.entities.Product;
import ru.haazad.java.architectures.repositories.ProductRepository;
import ru.haazad.java.architectures.utils.mappers.ProductRawMapper;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {

    private final JdbcTemplate jdbcTemplate;

    private final Map<Long, Product> identityMap = new ConcurrentHashMap<>();

    @Override
    public Product findById(Long id) {
        if (identityMap.containsKey(id)) {
            return identityMap.get(id);
        }
        Product product = jdbcTemplate.queryForObject("select * from products where id = ?", new Object[]{id},
                (rs, rowNum) -> Product.builder()
                        .id(rs.getLong("id"))
                        .title(rs.getString("title"))
                        .price(rs.getBigDecimal("price"))
                        .build());
        identityMap.put(product.getId(), product);
        return product;

    }

    @Override
    public List<Product> findAll() {
        identityMap.clear();
        List<Product> products = jdbcTemplate.query("select * from products", new ProductRawMapper());
        for (Product p: products) {
            identityMap.put(p.getId(), p);
        }
        return products;
    }

    @Override
    public void create(Product product) {
        jdbcTemplate.update("insert into products (title, price) values (?, ?)", product.getTitle(), product.getPrice());
    }

    @Override
    public void update(Product product) {
        jdbcTemplate.update("update products set title = ?, price = ?, update_time = sysdate where id = ?", product.getTitle(), product.getPrice(), product.getId());
        identityMap.remove(product.getId());
    }

    @Override
    public void deleteById(Long id) {
        jdbcTemplate.update("delete products where id = ?", id);
        identityMap.remove(id);
    }

    @Override
    public boolean haveProduct(Long productId) {
        int count = jdbcTemplate.queryForObject("select count(*) from products where id = ?", new Object[]{productId}, Integer.class);
        return count == 1;
    }
}
