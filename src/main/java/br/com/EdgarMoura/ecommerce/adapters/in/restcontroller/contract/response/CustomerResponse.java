package br.com.EdgarMoura.ecommerce.adapters.in.restcontroller.contract.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerResponse {
    private String customerId;
    private String name;
    private String cpf;
    private String email;
    private String address;
}