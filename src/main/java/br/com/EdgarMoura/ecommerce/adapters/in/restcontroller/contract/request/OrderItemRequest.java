package br.com.EdgarMoura.ecommerce.adapters.in.restcontroller.contract.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemRequest {

    @NotNull(message = "O campo quantity não pode ser nulo.")
    private Integer quantity;

    @Positive
    @NotNull(message = "O campo quantity não pode ser nulo.")
    private BigDecimal price;

    @Valid
    @NotNull(message = "O campo product não pode ser nulo.")
    private ProductIdRequest product;
}
