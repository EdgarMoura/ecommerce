package br.com.EdgarMoura.ecommerce.domain.model;

import br.com.EdgarMoura.ecommerce.adapters.in.restcontroller.contract.request.OrderRequest;
import br.com.EdgarMoura.ecommerce.adapters.in.restcontroller.contract.request.ProductRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {

    private Long id;
    private Integer quantity;
    private BigDecimal price;
    private Order order;
    private Product product;
}
