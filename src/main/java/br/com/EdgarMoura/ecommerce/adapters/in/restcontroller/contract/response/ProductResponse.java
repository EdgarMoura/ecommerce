package br.com.EdgarMoura.ecommerce.adapters.in.restcontroller.contract.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProductResponse {

    private Long productId;
    private String productName;
    private String details;
    private String category;
}
