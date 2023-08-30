package br.com.EdgarMoura.ecommerce.util;

import br.com.EdgarMoura.ecommerce.adapters.in.restcontroller.contract.request.CustomerRequest;
import br.com.EdgarMoura.ecommerce.adapters.out.datastore.entity.CustomerEntity;
import br.com.EdgarMoura.ecommerce.domain.model.Customer;

public class ObjectsBuilder {
    private ObjectsBuilder(){}

    public static Customer createCustomer() {
        return Customer.builder().customerId(1l).address("Rua do Fulano, 234, Centro")
                .cpf("25457403068").email("ciclano@gmai.com").name("Ciclano").build();
    }

    public static CustomerEntity createCustomerEntity() {
        return CustomerEntity.builder().customerId(1l).address("Rua do Fulano, 234, Centro")
                .cpf("25457403068").email("ciclano@gmai.com").name("Ciclano").build();
    }

    public static CustomerRequest createCustomerRequest() {
        return CustomerRequest.builder().address("Rua do Fulano, 234, Centro")
                .cpf("25457403068").email("ciclano@gmai.com").name("Ciclano").build();
    }
}
