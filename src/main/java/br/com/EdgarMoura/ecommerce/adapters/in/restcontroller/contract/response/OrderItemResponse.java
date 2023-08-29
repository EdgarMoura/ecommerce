package br.com.EdgarMoura.ecommerce.adapters.in.restcontroller.contract.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemResponse {

    private Long id;
    private Integer quantity;
    private String price;
    private ProductResponse product;
}
