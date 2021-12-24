package ru.haazad.java.architectures.utils.mappers;

import org.springframework.jdbc.core.RowMapper;
import ru.haazad.java.architectures.entities.Product;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRawMapper implements RowMapper<Product> {

    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Product.builder()
                .id(rs.getLong("id"))
                .title(rs.getString("title"))
                .price(rs.getBigDecimal("price"))
                .createDate(rs.getTimestamp("create_time"))
                .updateDate(rs.getTimestamp("update_time"))
                .build();
    }
}
