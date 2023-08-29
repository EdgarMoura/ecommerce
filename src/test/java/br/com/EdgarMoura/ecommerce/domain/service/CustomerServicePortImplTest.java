package br.com.EdgarMoura.ecommerce.domain.service;

import br.com.EdgarMoura.ecommerce.domain.exceptions.ExistingDataException;
import br.com.EdgarMoura.ecommerce.domain.exceptions.NotFoundException;
import br.com.EdgarMoura.ecommerce.domain.model.Customer;
import br.com.EdgarMoura.ecommerce.domain.ports.out.CustomerDataStorePort;
import br.com.EdgarMoura.ecommerce.util.ObjectsBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServicePortImplTest {

    @InjectMocks
    private CustomerServicePortImpl customerServicePort;

    @Mock
    private CustomerDataStorePort customerDataStorePort;

    private Customer customer;

    @BeforeEach
    void setup() {
        customer = ObjectsBuilder.createCustomer();
    }

    @Test
    void shouldValidateInsert() {
        when(customerDataStorePort.findByCpfCustomer(any())).thenReturn(false);

        Assertions.assertDoesNotThrow(() -> customerServicePort.insert(customer));
    }

    @Test
    void shouldValidateIfExistingCustomer() {
        when(customerDataStorePort.findByCpfCustomer(any())).thenReturn(true);

       assertThrows(ExistingDataException.class, () -> customerServicePort.insert(customer));
    }

    @Test
    void shouldValidateFindAll() {
        List<Customer> customerList = Arrays.asList(customer);
        when(customerDataStorePort.findAllCustomer()).thenReturn(customerList);

        assertEquals(1, customerServicePort.findAll().size());
        assertEquals("Ciclano", customerServicePort.findAll().get(0).getName());
    }

    @Test
    void shouldValidateFindById() throws NotFoundException {
        when(customerDataStorePort.findByIdCustomer(anyLong())).thenReturn(customer);

        Customer customer1 = customerServicePort.findById(anyLong());
        assertEquals("Ciclano", customer1.getName());
        assertEquals("25457403068", customer1.getCpf());

    }

    @Test
    void shouldReturnExceptionWhenFindByIdCustomer() throws NotFoundException {
        when(customerDataStorePort.findByIdCustomer(anyLong())).thenReturn(null);

        assertThrows(NotFoundException.class, () -> customerServicePort.findById(anyLong()));
    }

    @Test
    void shouldValidateUpdateCustomer() {
        when(customerDataStorePort.findByIdCustomer(anyLong())).thenReturn(customer);

        assertDoesNotThrow(() -> customerServicePort.updateCustomer(anyLong(), customer));
    }

    @Test
    void shouldReturnExceptionWhenFindCustomerWhenUpdate() throws NotFoundException {
        when(customerDataStorePort.findByIdCustomer(anyLong())).thenReturn(null);

        assertThrows(NotFoundException.class, () -> customerServicePort.updateCustomer(anyLong(), customer));
    }

    @Test
    void shouldValidateDeleteCustomer() {
        when(customerDataStorePort.deleteByIdCustomer(anyLong())).thenReturn(true);

        assertDoesNotThrow(() -> customerServicePort.deleteByIdCustomer(anyLong()));
    }

    @Test
    void shouldReturnExceptionWhenFindCustomerWhenDeleteCustomer() {
        when(customerDataStorePort.deleteByIdCustomer(anyLong())).thenReturn(false);

        assertThrows(NotFoundException.class, () -> customerServicePort.deleteByIdCustomer(anyLong()));
    }
}