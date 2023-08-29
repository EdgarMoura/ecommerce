package br.com.EdgarMoura.ecommerce.adapters.in.restcontroller.contract.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Setter
@Getter
public class CustomerOrderRequest {

    @NotNull(message = "O campo customerId não pode ser nulo")
    private Long customerId;
}
