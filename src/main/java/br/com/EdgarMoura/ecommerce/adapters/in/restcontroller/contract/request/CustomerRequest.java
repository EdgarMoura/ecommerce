package br.com.EdgarMoura.ecommerce.adapters.in.restcontroller.contract.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@Builder
public class CustomerRequest {

    @NotEmpty(message = "O campo name não pode ser nulo ou vazio.")
    private String name;

    @Email
    @NotEmpty(message = "O campo email não pode ser nulo ou vazio.")
    private String email;

    @CPF
    @NotEmpty(message = "O campo cpf não pode ser nulo ou vazio.")
    private String cpf;

    @Valid
    @NotNull(message = "O campo address não pode ser nulo.")
    private String address;
}
