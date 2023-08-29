package br.com.EdgarMoura.ecommerce.adapters.in.restcontroller.contract.request;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;

@Getter
@Builder
public class ProductRequest {

    @NotEmpty(message = "O campo productName não pode ser nulo ou vazio.")
    private String productName;
    private String details;

    @NotEmpty(message = "O campo category não pode ser nulo ou vazio.")
    private String category;
}
