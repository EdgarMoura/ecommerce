package br.com.EdgarMoura.ecommerce.adapters.in.restcontroller.contract.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {

    @Valid
    @NotNull(message = "O campo customer não pode ser nulo ou vazio.")
    private CustomerOrderRequest customer;

    @Valid
    @NotEmpty(message = "O campo items não pode ser nulo ou vazio.")
    private List<OrderItemRequest> items = new ArrayList<>();
}
