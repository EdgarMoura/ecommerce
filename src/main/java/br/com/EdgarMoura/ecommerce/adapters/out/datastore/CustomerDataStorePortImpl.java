package br.com.EdgarMoura.ecommerce.adapters.out.datastore;

import br.com.EdgarMoura.ecommerce.adapters.mapper.CustomerMapper;
import br.com.EdgarMoura.ecommerce.adapters.out.datastore.entity.CustomerEntity;
import br.com.EdgarMoura.ecommerce.adapters.out.datastore.repository.CustomerRepository;
import br.com.EdgarMoura.ecommerce.domain.model.Customer;
import br.com.EdgarMoura.ecommerce.domain.ports.out.CustomerDataStorePort;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class CustomerDataStorePortImpl implements CustomerDataStorePort {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public Customer salveCustomer(Customer customer) {
        CustomerEntity customerEntity = customerMapper.customerToCustomerEntity(customer);
        CustomerEntity clienteSalvo = customerRepository.save(customerEntity);
        log.info("Salvo na coleção de cliente_wishlist: {}", clienteSalvo.getCustomerId());
        return customerMapper.customerEntityToCustomer(clienteSalvo);
    }

    @Override
    public List<Customer> findAllCustomer() {
        List<CustomerEntity> customerEntityList = customerRepository.findAll();
        List<Customer> customers = customerMapper.listCustomerEntityToListCustomer(customerEntityList);
        log.info("Quantidades de cliente encontados: {}", customers.size());
        return customers;
    }

    @Override
    public Customer findByIdCustomer(Long id) {
        Optional<CustomerEntity> clienteEntityOptional = customerRepository.findById(id);
        if(clienteEntityOptional.isEmpty()) {
            log.error("Cliente com id {} não encontrado na base.", id);
            return null;
        }
        CustomerEntity customerEntity = clienteEntityOptional.get();
        log.info("Cliente encontrado com id: {}", customerEntity.getCustomerId());
        return customerMapper.customerEntityToCustomer(customerEntity);
    }

    @Override
    public boolean deleteByIdCustomer(Long id) {
        if(customerRepository.existsById(id)){
            customerRepository.deleteById(id);
            log.info("Cliente excluído da base de dados com sucesso.");
            return true;
        }
        log.error("Cliente com id {} não encontrado para exclusão.", id);
        return false;
    }

    @Override
    public boolean findByCpfCustomer(Customer customer) {
        if(customerRepository.findByCpf(customer.getCpf()).isPresent()){
            log.info("Cliente encontrado com documento de numero {}.", customer.getCpf());
            return true;
        }
        log.error("Cliente não encontrado com documento de número {}.", customer.getCpf());
        return false;
    }
}
