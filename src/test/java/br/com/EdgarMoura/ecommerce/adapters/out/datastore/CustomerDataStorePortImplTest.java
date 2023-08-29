package br.com.EdgarMoura.ecommerce.adapters.out.datastore;

import br.com.EdgarMoura.ecommerce.adapters.mapper.CustomerMapper;
import br.com.EdgarMoura.ecommerce.adapters.out.datastore.entity.CustomerEntity;
import br.com.EdgarMoura.ecommerce.adapters.out.datastore.repository.CustomerRepository;
import br.com.EdgarMoura.ecommerce.domain.model.Customer;
import br.com.EdgarMoura.ecommerce.util.ObjectsBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerDataStorePortImplTest {

    @InjectMocks
    private CustomerDataStorePortImpl customerDataStorePort;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerMapper customerMapper;

    private CustomerEntity customerEntity;
    private Customer customer;

    @BeforeEach
    void setup() {
        customerEntity = ObjectsBuilder.createCustomerEntity();
        customer = ObjectsBuilder.createCustomer();
    }

    @Test
    void shouldSalveCustomer() {
        when(customerMapper.customerToCustomerEntity(any())).thenReturn(customerEntity);
        when(customerRepository.save(any())).thenReturn(customerEntity);
        when(customerMapper.customerEntityToCustomer(any())).thenReturn(customer);

        customerRepository.save(customerEntity);
        verify(customerRepository).save(customerEntity);
        assertDoesNotThrow(() -> customerDataStorePort.salveCustomer(customer));
    }

    @Test
    void shouldFindAllCustomer() {
        when(customerRepository.findAll()).thenReturn(Arrays.asList(customerEntity));
        when(customerMapper.listCustomerEntityToListCustomer(any())).thenReturn(Arrays.asList(customer));

        assertDoesNotThrow(() -> customerDataStorePort.findAllCustomer());
        assertEquals(1,  customerDataStorePort.findAllCustomer().size());
        assertEquals("Ciclano",  customerDataStorePort.findAllCustomer().get(0).getName());
    }

    @Test
    void shouldFindByIdCustomer() {
        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customerEntity));
        when(customerMapper.customerEntityToCustomer(any())).thenReturn(customer);


        assertDoesNotThrow(() -> customerDataStorePort.findByIdCustomer(anyLong()));
        assertEquals("Ciclano",  customerDataStorePort.findByIdCustomer(anyLong()).getName());
    }

    @Test
    void shouldReturnNullWhenFindByIdCustomer() {
        when(customerRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertNull(customerDataStorePort.findByIdCustomer(anyLong()));
    }

    @Test
    void shouldReturnFalseWhenFindByIdCustomerWhenDeleteCustomer() {
        when(customerRepository.existsById(anyLong())).thenReturn(false);

        assertFalse(customerDataStorePort.deleteByIdCustomer(anyLong()));
    }

    @Test
    void shouldReturnTrueWhenDeleteCustomer() {
        when(customerRepository.existsById(anyLong())).thenReturn(true);

        assertTrue(customerDataStorePort.deleteByIdCustomer(anyLong()));
    }

    @Test
    void shouldFindByCpfCustomer() {
        when(customerRepository.findByCpf(anyString())).thenReturn(Optional.of(customerEntity));

        assertTrue(customerDataStorePort.findByCpfCustomer(customer));
    }

    @Test
    void shouldReturnFalseWhenFindByCpfCustomer() {
        when(customerRepository.findByCpf(anyString())).thenReturn(Optional.empty());

        assertFalse(customerDataStorePort.findByCpfCustomer(customer));
    }
}