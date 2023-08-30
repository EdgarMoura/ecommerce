package br.com.EdgarMoura.ecommerce.adapters.in.restcontroller;

import br.com.EdgarMoura.ecommerce.adapters.in.restcontroller.contract.request.CustomerRequest;
import br.com.EdgarMoura.ecommerce.adapters.mapper.CustomerMapper;
import br.com.EdgarMoura.ecommerce.domain.exceptions.ExistingDataException;
import br.com.EdgarMoura.ecommerce.domain.exceptions.NotFoundException;
import br.com.EdgarMoura.ecommerce.domain.model.Customer;
import br.com.EdgarMoura.ecommerce.domain.ports.in.CustomerServicePort;
import br.com.EdgarMoura.ecommerce.util.ObjectsBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    @MockBean
    private CustomerServicePort customerServicePort;

    @MockBean
    private CustomerMapper customerMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    private Customer customer;
    private CustomerRequest customerRequest;

    @BeforeEach
    void setup() {
        customer = ObjectsBuilder.createCustomer();
        customerRequest = ObjectsBuilder.createCustomerRequest();
    }

    @Test
    void shouldReturn200WhenFindAllCustomer() throws Exception {
        when(customerServicePort.findAll()).thenReturn(Arrays.asList(customer));

        mockMvc.perform(get("/customers")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturn200WhenFindByIdCustomer() throws Exception {
        when(customerServicePort.findById(anyLong())).thenReturn(customer);

        mockMvc.perform(get("/customers/{id}", 1l)
                        .content(mapper.writeValueAsString(customerRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturn404WhenFindByIdCustomer() throws Exception {
        when(customerServicePort.findById(anyLong())).thenThrow(NotFoundException.class);

        mockMvc.perform(get("/customers/{id}", 1l)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturn200WhenInsertCustomer() throws Exception {
        when(customerMapper.customerRequestToCustomer(any())).thenReturn(customer);
        when(customerServicePort.insert(any())).thenReturn(customer);

        mockMvc.perform(post("/customers")
                        .content(mapper.writeValueAsString(customerRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void shouldReturnBadRequestWhenInsertCustomer() throws Exception {
        when(customerMapper.customerRequestToCustomer(any())).thenReturn(customer);
        when(customerServicePort.insert(any())).thenReturn(customer);
        customerRequest.setCpf(null);

        mockMvc.perform(post("/customers")
                        .content(mapper.writeValueAsString(customerRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturn409WhenInsertCustomer() throws Exception {
        when(customerMapper.customerRequestToCustomer(any())).thenReturn(customer);
        when(customerServicePort.insert(any())).thenThrow(ExistingDataException.class);

        mockMvc.perform(post("/customers")
                        .content(mapper.writeValueAsString(customerRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict());
    }

    @Test
    void shouldReturn200WhenUpdateCustomer() throws Exception {
        when(customerMapper.customerRequestToCustomer(any())).thenReturn(customer);
        when(customerServicePort.updateCustomer(anyLong(), any())).thenReturn(customer);

        mockMvc.perform(put("/customers/{id}", 1l)
                        .content(mapper.writeValueAsString(customerRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnBadRequest400WhenUpdateCustomer() throws Exception {
        when(customerMapper.customerRequestToCustomer(any())).thenReturn(customer);
        when(customerServicePort.updateCustomer(anyLong(), any())).thenReturn(customer);
        customerRequest.setCpf(null);

        mockMvc.perform(put("/customers/{id}", 1l)
                        .content(mapper.writeValueAsString(customerRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturn404WhenUpdateCustomer() throws Exception {
        when(customerMapper.customerRequestToCustomer(any())).thenReturn(customer);
        when(customerServicePort.updateCustomer(anyLong(), any())).thenThrow(NotFoundException.class);

        mockMvc.perform(put("/customers/{id}", 1l)
                        .content(mapper.writeValueAsString(customerRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturn204WhenDeleteByIdCustomer() throws Exception {
       doNothing().when(customerServicePort).deleteByIdCustomer(anyLong());

        mockMvc.perform(delete("/customers/{id}", 1l)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void shouldReturn404WhenDeleteByIdCustomer() throws Exception {
        doThrow(NotFoundException.class).when(customerServicePort).deleteByIdCustomer(anyLong());

        mockMvc.perform(delete("/customers/{id}", 1l)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}