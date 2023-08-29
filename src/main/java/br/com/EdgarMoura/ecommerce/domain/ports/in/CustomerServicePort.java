package br.com.EdgarMoura.ecommerce.domain.ports.in;

import br.com.EdgarMoura.ecommerce.domain.exceptions.ExistingDataException;
import br.com.EdgarMoura.ecommerce.domain.exceptions.NotFoundException;
import br.com.EdgarMoura.ecommerce.domain.model.Customer;

import java.util.List;

public interface CustomerServicePort {

   Customer insert(Customer customer) throws ExistingDataException;

   List<Customer> findAll();

   Customer findById(Long id) throws NotFoundException;

   Customer updateCustomer(Long id, Customer customer) throws NotFoundException;

   void deleteByIdCustomer(Long id) throws NotFoundException;
}
