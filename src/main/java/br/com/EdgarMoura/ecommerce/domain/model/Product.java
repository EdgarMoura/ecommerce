package br.com.EdgarMoura.ecommerce.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Product {

    private Long productId;
    private String productName;
    private String details;
    private String category;
}
