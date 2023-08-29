package br.com.EdgarMoura.ecommerce.domain.service;

import br.com.EdgarMoura.ecommerce.domain.exceptions.ExistingDataException;
import br.com.EdgarMoura.ecommerce.domain.exceptions.NotFoundException;
import br.com.EdgarMoura.ecommerce.domain.model.Customer;
import br.com.EdgarMoura.ecommerce.domain.ports.in.CustomerServicePort;
import br.com.EdgarMoura.ecommerce.domain.ports.out.CustomerDataStorePort;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class CustomerServicePortImpl implements CustomerServicePort {

    private final CustomerDataStorePort customerDataStorePort;

    @Override
    public Customer insert(Customer customer) throws ExistingDataException {
        if(customerDataStorePort.findByCpfCustomer(customer)) {
            throw new ExistingDataException("Cliente já existente.");
        }
        return customerDataStorePort.salveCustomer(customer);
    }

    @Override
    public List<Customer> findAll() {
        return customerDataStorePort.findAllCustomer();
    }

    @Override
    public Customer findById(Long id) throws NotFoundException {
        Customer customerFound = customerDataStorePort.findByIdCustomer(id);
        if(customerFound == null) {
            throw new NotFoundException("Cliente não encontrado.");
        }
        return customerFound;
    }

    @Override
    public Customer updateCustomer(Long id, Customer customer) throws NotFoundException {
        Customer customerFound = customerDataStorePort.findByIdCustomer(id);
        if(customerFound == null) {
            throw new NotFoundException("Cliente não encontrado para atualização.");
        }
        customerFound.setName(customer.getName());
        customerFound.setCpf(customer.getCpf());
        customerFound.setEmail(customer.getEmail());
        customerFound.setAddress(customer.getAddress());
        return customerDataStorePort.salveCustomer(customerFound);
    }

    @Override
    public void deleteByIdCustomer(Long id) throws NotFoundException {
        if(!customerDataStorePort.deleteByIdCustomer(id)) {
            throw new NotFoundException("Cliente não encontrado para exclusão.");
        }
    }
}
