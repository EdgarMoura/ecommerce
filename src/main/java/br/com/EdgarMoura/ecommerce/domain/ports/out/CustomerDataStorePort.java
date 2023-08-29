package br.com.EdgarMoura.ecommerce.domain.ports.out;

import br.com.EdgarMoura.ecommerce.domain.model.Customer;

import java.util.List;

public interface CustomerDataStorePort {

    Customer salveCustomer(Customer customer);

    List<Customer> findAllCustomer();

    Customer findByIdCustomer(Long id);

    boolean deleteByIdCustomer(Long id);

    boolean findByCpfCustomer(Customer customer);
}
