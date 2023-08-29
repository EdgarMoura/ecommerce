package br.com.EdgarMoura.ecommerce.adapters.in.restcontroller;

import br.com.EdgarMoura.ecommerce.adapters.in.restcontroller.contract.request.CustomerRequest;
import br.com.EdgarMoura.ecommerce.adapters.in.restcontroller.contract.response.CustomerResponse;
import br.com.EdgarMoura.ecommerce.adapters.mapper.CustomerMapper;
import br.com.EdgarMoura.ecommerce.domain.exceptions.ExistingDataException;
import br.com.EdgarMoura.ecommerce.domain.exceptions.NotFoundException;
import br.com.EdgarMoura.ecommerce.domain.model.Customer;
import br.com.EdgarMoura.ecommerce.domain.ports.in.CustomerServicePort;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerServicePort customerServicePort;

    @Autowired
    private CustomerMapper customerMapper;

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> findAll() {
        List<Customer> customers = customerServicePort.findAll();
        customerMapper.listCustomersToListCustomersResponse(customers);

        return ResponseEntity.ok(customerMapper.listCustomersToListCustomersResponse(customers));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> findById(@PathVariable Long id) {
        Customer customer = null;
        try {
            customer = customerServicePort.findById(id);
           return ResponseEntity.ok(customerMapper.customerToCustomerResponse(customer));
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<CustomerResponse> insertCustomer(@Valid @RequestBody CustomerRequest customerRequest, UriComponentsBuilder uriBuilder) {
        try {
            Customer customer = customerServicePort.insert(customerMapper.customerRequestToCustomer(customerRequest));
            URI address = uriBuilder.path("/customers/{id}").buildAndExpand(customer.getCustomerId()).toUri();
            return ResponseEntity.created(address).body(customerMapper.customerToCustomerResponse(customer));
        } catch (ExistingDataException e) {
            log.error(e.getMessage());
           return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponse> updateCustomer(@PathVariable Long id, @RequestBody @Valid CustomerRequest customerRequest) {
        try {
            Customer customer = customerServicePort.updateCustomer(id, customerMapper.customerRequestToCustomer(customerRequest));
            return ResponseEntity.ok(customerMapper.customerToCustomerResponse(customer));
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        try {
            customerServicePort.deleteByIdCustomer(id);
            return ResponseEntity.noContent().build();
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
