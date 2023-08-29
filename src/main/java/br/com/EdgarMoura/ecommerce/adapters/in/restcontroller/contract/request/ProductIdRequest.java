package br.com.EdgarMoura.ecommerce.adapters.in.restcontroller.contract.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductIdRequest {

    @NotNull(message = "O campo customer não pode ser nulo.")
    private Long productId;
}
