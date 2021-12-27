package ru.haazad.java.architectures.entities;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
public class Product {

    private Long id;

    private String title;

    private BigDecimal price;

    private Timestamp createDate;

    private Timestamp updateDate;

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final Product product = new Product();

        public Builder() {}

        public Builder id(Long id) {
            product.id = id;
            return this;
        }

        public Builder title(String title) {
            product.title = title;
            return this;
        }

        public Builder price(BigDecimal price) {
            product.price = price;
            return this;
        }

        public Builder createDate(Timestamp dateTime) {
            product.createDate = dateTime;
            return this;
        }

        public Builder updateDate(Timestamp dateTime) {
            product.updateDate = dateTime;
            return this;
        }

        public Product build() {
            return product;
        }
    }

}
