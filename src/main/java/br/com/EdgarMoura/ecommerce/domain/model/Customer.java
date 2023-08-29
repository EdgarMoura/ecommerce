package br.com.EdgarMoura.ecommerce.domain.model;


import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    private Long customerId;
    private String name;
    private String cpf;
    private String email;
    private String address;
}
