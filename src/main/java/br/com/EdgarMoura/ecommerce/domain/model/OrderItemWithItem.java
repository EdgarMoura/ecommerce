package br.com.EdgarMoura.ecommerce.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemWithItem {

    private Long id;
    private Integer quantity;
    private BigDecimal price;
    private Product product;
}
