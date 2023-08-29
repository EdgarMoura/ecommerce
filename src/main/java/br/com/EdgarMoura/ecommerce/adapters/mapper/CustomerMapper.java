package br.com.EdgarMoura.ecommerce.adapters.mapper;

import br.com.EdgarMoura.ecommerce.adapters.in.restcontroller.contract.request.CustomerOrderRequest;
import br.com.EdgarMoura.ecommerce.adapters.in.restcontroller.contract.request.CustomerRequest;
import br.com.EdgarMoura.ecommerce.adapters.in.restcontroller.contract.response.CustomerResponse;
import br.com.EdgarMoura.ecommerce.adapters.out.datastore.entity.CustomerEntity;
import br.com.EdgarMoura.ecommerce.domain.model.Customer;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    Customer customerRequestToCustomer(CustomerRequest source);

    Customer customerOrderRequestToCustomer(CustomerOrderRequest source);

    CustomerResponse customerToCustomerResponse(Customer source);

    List<CustomerResponse> listCustomersToListCustomersResponse(List<Customer> source);

    CustomerEntity customerToCustomerEntity(Customer customer);

    Customer customerEntityToCustomer(CustomerEntity customerEntity);

    List<Customer> listCustomerEntityToListCustomer(List<CustomerEntity> source);
}
